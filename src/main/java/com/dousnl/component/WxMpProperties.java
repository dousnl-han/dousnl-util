package com.dousnl.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取类
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 14:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxMpProperties {

    /**
     * 公众号appId
     */
    private String appId;

    /**
     * 公众号appSecret
     */
    private String secret;

    /**
     * 公众号token
     */
    private String token;

    /**
     * 公众号aesKey
     */
    private String aesKey;
}
