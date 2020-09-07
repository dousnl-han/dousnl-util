package com.dousnl.component;

import com.dousnl.component.chanjar.DataUtils;
import com.dousnl.component.chanjar.Utf8ResponseHandler;
import com.dousnl.component.chanjar.WxAccessToken;
import com.dousnl.component.chanjar.WxType;
import com.dousnl.component.error.WxError;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import static com.dousnl.component.WxMpApiUrl.TemplateMsg.GET_ACCESS_TOKEN_URL;

/**
 * 微信发送模板消息实现
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 13:06
 */
@Slf4j
@Service
public class WxMpServiceImpl implements WxMpService {

    private Map<String, WxMpConfigStorage> configStorageMap;

    private int retrySleepMillis = 1000;
    private int maxRetryTimes = 5;

    @Override
    public String post(WxMpApiUrl wxMpApiUrl, String postData) throws WxErrorException {
        String url = wxMpApiUrl.getUrl(this.getWxMpConfigStorage());
        return execute(url, postData);
    }

    @Override
    public <T, E> T execute(String url, E postData) throws WxErrorException {
        int retryTimes = 0;
        do {
            try {
                return this.executeInternal(url, postData);
            } catch (WxErrorException e) {
                if (retryTimes + 1 > this.maxRetryTimes) {
                    log.warn("重试达到最大次数【{}】", maxRetryTimes);
                    //最后一次重试失败后，直接抛出异常，不再等待
                    throw new RuntimeException("微信服务端异常，超出重试次数");
                }
                WxError error = e.getError();
                // -1 系统繁忙, 1000ms后重试
                if (error.getErrorCode() == -1) {
                    int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
                    try {
                        log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e1) {
                        throw new RuntimeException(e1);
                    }
                } else {
                    throw e;
                }
            }
        } while (retryTimes++ < this.maxRetryTimes);

        log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
        throw new RuntimeException("微信服务端异常，超出重试次数");
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        final WxMpConfigStorage config = this.getWxMpConfigStorage();
        if (!config.isAccessTokenExpired() && !forceRefresh) {
            return config.getAccessToken();
        }

        Lock lock = config.getAccessTokenLock();
        lock.lock();
        try {
            if (!config.isAccessTokenExpired() && !forceRefresh) {
                return config.getAccessToken();
            }
            String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(config), config.getAppId(), config.getSecret());
            try {
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpClient httpClient = HttpClients.createDefault();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    return this.extractAccessToken(new BasicResponseHandler().handleResponse(response));
                } finally {
                    httpGet.releaseConnection();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } finally {
            lock.unlock();
        }
    }

    protected <T, E> T executeInternal(String url, E data) throws WxErrorException {
        E dataForLog = DataUtils.handleDataWithSecret(data);

        if (url.contains("access_token=")) {
            throw new IllegalArgumentException("uri参数中不允许有access_token: " + url);
        }

        String accessToken = getAccessToken(false);
        String uriWithAccessToken = url + (url.contains("?") ? "&" : "?") + "access_token=" + accessToken;

        try {
            T result = doExecute(uriWithAccessToken, data, WxType.MP);
            log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog, result);
            return result;
        } catch (WxErrorException e) {
            WxError error = e.getError();
            /*
             * 发生以下情况时尝试刷新access_token
             * 40001 获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真比对 AppSecret 的正确性，或查看是否正在为恰当的公众号调用接口
             * 42001 access_token 超时，请检查 access_token 的有效期，请参考基础支持 - 获取 access_token 中，对 access_token 的详细机制说明
             * 40014 不合法的 access_token ，请开发者认真比对 access_token 的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
             */
            if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001 || error.getErrorCode() == 40014) {
                // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
                Lock lock = this.getWxMpConfigStorage().getAccessTokenLock();
                lock.lock();
                try {
                    if (StringUtils.equals(this.getWxMpConfigStorage().getAccessToken(), accessToken)) {
                        this.getWxMpConfigStorage().expireAccessToken();
                    }
                } catch (Exception ex) {
                    this.getWxMpConfigStorage().expireAccessToken();
                } finally {
                    lock.unlock();
                }
                if (this.getWxMpConfigStorage().autoRefreshToken()) {
                    return this.execute(url, data);
                }
            }

            if (error.getErrorCode() != 0) {
                log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, dataForLog, error);
                throw new WxErrorException(error, e);
            }
            return null;
        } catch (IOException e) {
            log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, dataForLog, e.getMessage());
            throw new WxErrorException(new WxError(e.getMessage()), e);
        }
    }

    @Override
    public <T, E> T doExecute(String uri, E postEntity, WxType wxType) throws IOException, WxErrorException {
        HttpPost httpPost = new HttpPost(uri);
        if (postEntity != null) {
            StringEntity entity = new StringEntity(postEntity.toString(), Consts.UTF_8);
            entity.setContentType("application/json; charset=utf-8");
            httpPost.setEntity(entity);
            log.info(">>>>>>>>>>>>>postEntity.tostring:{}",postEntity.toString());
        }
        T var8;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            Throwable var6 = null;

            try {
                String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
                var8 = (T) this.handleResponse(wxType, responseContent);
            } catch (Throwable var24) {
                var6 = var24;
                throw var24;
            } finally {
                if (response != null) {
                    if (var6 != null) {
                        try {
                            response.close();
                        } catch (Throwable var23) {
                            var6.addSuppressed(var23);
                        }
                    } else {
                        response.close();
                    }
                }

            }
        } finally {
            httpPost.releaseConnection();
        }
        return var8;
    }

    @Override
    public void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider) {
        final String defaultMpId = WxMpConfigStorageHolder.get();
        this.setMultiConfigStorages(ImmutableMap.of(defaultMpId, wxConfigProvider), defaultMpId);
    }


    @Override
    public void setMultiConfigStorages(Map<String, WxMpConfigStorage> configStorages, String defaultMpId) {
        this.configStorageMap = Maps.newHashMap(configStorages);
        WxMpConfigStorageHolder.set(defaultMpId);
    }

    public String handleResponse(WxType wxType, String responseContent) throws WxErrorException {
        if (responseContent.isEmpty()) {
            throw new WxErrorException(new WxError(9999,"无响应内容"));
        } else if (responseContent.startsWith("<xml>")) {
            return responseContent;
        } else {
            WxError error = WxError.fromJson(responseContent, wxType);
            if (error.getErrorCode() != 0) {
                throw new WxErrorException(error);
            } else {
                return responseContent;
            }
        }
    }


    public static <E> E handleDataWithSecret(E data) {
        E dataForLog = data;
        if (data instanceof String && StringUtils.contains((String) data, "&secret=")) {
            dataForLog = (E) StringUtils.replace((String) data, "&secret=\\w+&", "&secret=******&");
        }

        return dataForLog;
    }

    @Override
    public WxMpConfigStorage getWxMpConfigStorage() {
        if (this.configStorageMap.size() == 1) {
            // 只有一个公众号，直接返回其配置即可
            return this.configStorageMap.values().iterator().next();
        }

        return this.configStorageMap.get(WxMpConfigStorageHolder.get());
    }

    protected String extractAccessToken(String resultContent) throws WxErrorException {
        WxMpConfigStorage config = this.getWxMpConfigStorage();
        WxError error = WxError.fromJson(resultContent, WxType.MP);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        log.info(">>>>>>>>>>>resultContent：{}",resultContent);
        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        log.info(">>>>>>>>>>>accessToken信息：{}",accessToken);
        config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
        return config.getAccessToken();
    }
}
