package com.dousnl.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置类
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 14:32
 */
@Configuration
public class WxConfig {
    private final WxMpProperties wxMpProperties;

    /**
     * 构造注入
     *
     * @param wxMpProperties
     */
    WxConfig(WxMpProperties wxMpProperties) {
        this.wxMpProperties = wxMpProperties;
    }
    /**
     * 微信客户端配置存储
     *
     * @return
     */
    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
        // 公众号appId
        configStorage.setAppId(wxMpProperties.getAppId());
        // 公众号appSecret
        configStorage.setSecret(wxMpProperties.getSecret());
        // 公众号Token
        configStorage.setToken(wxMpProperties.getToken());
        // 公众号EncodingAESKey
        configStorage.setAesKey(wxMpProperties.getAesKey());
        return configStorage;
    }

    /**
     * 声明实例
     *
     * @return
     */
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
}
