package com.dousnl.component;

import com.dousnl.component.chanjar.WxAccessToken;
import java.util.concurrent.locks.Lock;

/**
 * 微信配置存储类
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 11:34
 */
public interface WxMpConfigStorage {

    /**
     * 得到微信接口地址域名部分的自定义设置信息.
     */
    WxMpHostConfig getHostConfig();

    boolean isAccessTokenExpired();

    String getAccessToken();

    Lock getAccessTokenLock();

    String getAppId();

    String getSecret();

    /**
     * 应该是线程安全的.
     *
     * @param accessToken 要更新的WxAccessToken对象
     */
    void updateAccessToken(WxAccessToken accessToken);
    /**
     * 应该是线程安全的.
     *
     * @param accessToken      新的accessToken值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateAccessToken(String accessToken, int expiresInSeconds);

    /**
     * 强制将access token过期掉.
     */
    void expireAccessToken();

    /**
     * 是否自动刷新token.
     */
    boolean autoRefreshToken();
}
