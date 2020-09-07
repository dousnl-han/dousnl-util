package com.dousnl.component;

import com.dousnl.component.error.WxError;

/**
 * 微信异常
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 18:02
 */
public class WxErrorException extends Exception {
    private static final long serialVersionUID = -6357149550353160810L;
    private final WxError error;

    public WxErrorException(WxError error) {
        super(error.toString());
        this.error = error;
    }

    public WxErrorException(WxError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public WxError getError() {
        return this.error;
    }
}
