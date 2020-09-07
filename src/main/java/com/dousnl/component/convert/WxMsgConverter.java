package com.dousnl.component.convert;

/**
 * 消息拼装
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/13 15:15
 */
public interface WxMsgConverter<T> {

    /**
     * 转化
     * @param t
     */
    public void convert(T t);
}
