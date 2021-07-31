package com.dousnl.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.component.chanjar.WxType;
import com.dousnl.component.error.WxError;
import com.dousnl.domain.wechat.WeChatData;
import com.dousnl.domain.wechat.WxTemplateData;
import com.dousnl.domain.wechat.WxTemplateMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.dousnl.component.WxMpApiUrl.TemplateMsg.*;

/**
 * 发送消息
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 11:09
 */
@Slf4j
@Component
public class WxMsgPush {

    @Value("${wx.message.templateId}")
    private String templateId;

    @Autowired
    private WxMpService wxMpService;
    /**
     * 发送微信模板信息
     *
     * @param openId 接受者openId
     * @return 是否推送成功
     */
    public Boolean sendWxMsg(String openId) {
        // 发送模板消息接口
        WxTemplateMessage templateMessage = WxTemplateMessage.builder()
                // 接收者openid
                .touser(openId)
                // 模板id
                .template_id(templateId)
                // 模板跳转链接
                .url("http://www.baidu.com")
                .build();
        // 添加模板数据
        WxTemplateData data=new WxTemplateData();
        data.setFirst(new WeChatData("收到一条新订单","#173177"));
        data.setKeyword1(new WeChatData("测试商家","#173177"));
        data.setKeyword2(new WeChatData("13162505297","#173177"));
        data.setKeyword3(new WeChatData("AT000001","#173177"));
        data.setKeyword4(new WeChatData("成功","#173177"));
        data.setKeyword5(new WeChatData("100","#173177"));
        data.setRemark(new WeChatData("测试订单状态","#173177"));
        templateMessage.setData(data);

        //RenewalsOf365 s65=new RenewalsOf365("AT001111","韩亮","2020年7月20日");
        //s65.convertAboutToExpire(templateMessage);

        String msgId = null;
        try {
            // 发送模板消息
            msgId = sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("=========发送模板消息失败", e);
        }
        log.warn("·==++--·推送微信模板信息：{}·--++==·,msgId:{}", msgId != null ? "成功" : "失败", msgId);
        return msgId != null;
    }

    private String sendTemplateMsg(WxTemplateMessage templateMessage) throws WxErrorException {
        String responseContent = this.wxMpService.post(MESSAGE_TEMPLATE_SEND, JSON.toJSONString(templateMessage));
        final JSONObject jsonObject = JSON.parseObject(responseContent);
        if (0 == (Integer) jsonObject.get("errcode")) {
            return jsonObject.get("msgid").toString();
        }
        throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
    }

//    2.获取token，通过code参数加上AppID和AppSecret等，通过API换取access_token（后端开始干活了）
//    注意：下面的地址，都可以实用浏览器请求，从而获得相应的参数
//
//    https://api.weixin.qq.com/sns/oauth2/access_token?appid=微信提供appid&secret=微信提供secret&code=app提供的code&grant_type=authorization_code
//
//    返回数据：
//
//    {"access_token":"11_WOwLoiHkXWFX-grz2UufyGkDiw4QkwvckJ-7-5tjQemU7mtZz7JJFDiJH6B89Bo0XcjBzywneJxxuRGBL5CATkUmILnYwclyoAYJ8AXkjN0",
//            "expires_in":7200,
//            "refresh_token":"11_IJb0SydrI_WZOsNfEHzzGoOH1XYR04sRXTSP2RLLSYtxQlnLdiWYlA5hk2efQ-QUW_0HUvzftHWJOFMPfVqimk3wp-QveWNPy-tDNO43MKk",
//            "openid":"oMdaH0ygy6Tvu0snY6N32X8pYC4c",
//            "scope":"snsapi_userinfo",
//            "unionid":"odicx1skqmfjjvMbH3JLs2lZ8kSY"}

    public String getunionId(String openId) {
        String url = "";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return null;
    }

    public String getcode() throws UnsupportedEncodingException {
        //这里要将你的授权回调地址处理一下，否则微信识别不了
        String redirect_uri=URLEncoder.encode("http://localhost:9998/wechat/code", "UTF-8");
        //简单获取openid的话参数response_type与scope与state参数固

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx685a952ac2b70b91" +
                "&redirect_uri="+redirect_uri+"&response_type=code&scope=SCOPE" +
                "&state=STATE&connect_redirect=1#wechat_redirect";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return new BasicResponseHandler().handleResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    public String getUserInfo(String openid) throws WxErrorException {
        String accessToken = wxMpService.getAccessToken(false);
        if (accessToken==null){
            System.out.println("非樊登读书服务号");
            return "非樊登读书服务号";
        }
        String url = "https://api.weixin.qq.com/cgi-bin/user/info";
        String uriWithAccessToken = url + (url.contains("?") ? "&" : "?") + "access_token=" + accessToken;
        String uri=uriWithAccessToken+"&openid=" + "oSCOqszNVRmGbSCVDsEJIBULGeC1" + "&lang=zh_CN";
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return new BasicResponseHandler().handleResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    public String gettoken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx685a952ac2b70b91&secret=f919f62a24724171b13f529c7672b591" +
                "&code="+code+"&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return new BasicResponseHandler().handleResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }
}
