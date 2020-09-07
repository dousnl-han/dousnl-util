package com.dousnl.component.chanjar;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import java.io.Serializable;

/**
 * access token.
 *
 * @author hanliangliang
 */
@Data
public class WxAccessToken implements Serializable {
  private static final long serialVersionUID = 8709719312922168909L;

  private String accessToken;

  private int expiresIn = -1;

  public static WxAccessToken fromJson(String json) {
    return JSON.parseObject(json, WxAccessToken.class);
  }

}
