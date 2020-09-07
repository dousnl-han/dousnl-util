package com.dousnl.domain.wechat;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 9:51
 */
@Data
public class MiniProgram implements Serializable {

    private String appid;
    private String pagepath;

    /**
     * 是否使用path，否则使用pagepath.
     * 加入此字段是基于微信官方接口变化多端的考虑
     */
    private boolean usePath = false;

}
