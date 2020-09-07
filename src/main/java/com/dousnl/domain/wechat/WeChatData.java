package com.dousnl.domain.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 微信模板消息data
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatData implements Serializable {
    private String value;
    private String color;


}
