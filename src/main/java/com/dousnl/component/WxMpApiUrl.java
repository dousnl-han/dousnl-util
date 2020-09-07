package com.dousnl.component;

import lombok.AllArgsConstructor;
import static com.dousnl.component.WxMpHostConfig.*;

/**
 * 微信API-URL
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 11:30
 */
public interface WxMpApiUrl {
    /**
     * 得到api完整地址.
     *
     * @param config 微信公众号配置
     * @return api地址
     */
    String getUrl(WxMpConfigStorage config);


    @AllArgsConstructor
    enum TemplateMsg implements WxMpApiUrl{
        /**
         * send.
         */
        MESSAGE_TEMPLATE_SEND(API_DEFAULT_HOST_URL, "/cgi-bin/message/template/send"),
        /**
         * 获取access_token.
         */
        GET_ACCESS_TOKEN_URL(API_DEFAULT_HOST_URL, "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s");


        private String prefix;
        private String path;

        @Override
        public String getUrl(WxMpConfigStorage config) {
            return buildUrl(config.getHostConfig(), prefix, path);
        }
    }
}
