package com.dousnl.component;

import com.dousnl.component.chanjar.WxAccessToken;
import lombok.Data;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认配置实现
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 14:34
 */
@Data
public class WxMpDefaultConfigImpl implements WxMpConfigStorage{

    protected volatile String appId;
    protected volatile String secret;
    protected volatile String token;
    protected volatile String aesKey;
    protected volatile long expiresTime;
    protected volatile String accessToken;

    protected Lock accessTokenLock = new ReentrantLock();

    @Override
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    @Override
    public synchronized void updateAccessToken(WxAccessToken accessToken) {
        updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }

    @Override
    public void expireAccessToken() {
        this.expiresTime = 0;
    }

    @Override
    public boolean autoRefreshToken() {
        return true;
    }

    @Override
    public WxMpHostConfig getHostConfig() {
        return null;
    }
}
