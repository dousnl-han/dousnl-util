package com.dousnl.domain.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 9:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxTemplateData implements Serializable {

    private WeChatData first;
    private WeChatData keyword1;
    private WeChatData keyword2;
    private WeChatData keyword3;
    private WeChatData keyword4;
    private WeChatData keyword5;
    private WeChatData remark;

}
