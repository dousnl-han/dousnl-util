package com.dousnl.domain.wechat;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * 微信模板消息
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 9:48
 */
@Data
@Builder
public class WxTemplateMessage implements Serializable {

    private String touser;

    private String template_id;

    private String url;

    private MiniProgram miniprogram;

    @Builder.Default
    private WxTemplateData data;

}
