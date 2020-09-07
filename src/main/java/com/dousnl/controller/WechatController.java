package com.dousnl.controller;

import com.dousnl.component.WxErrorException;
import com.dousnl.component.WxMsgPush;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/10 9:45
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMsgPush wxMsgPush;

    @PostMapping(value = "/sendWxInfo")
    public boolean calculate(@RequestBody String openId){
        Boolean aBoolean =wxMsgPush.sendWxMsg(openId);
        return aBoolean;
    }

    @PostMapping(value = "/getunionId")
    public String getunionId(@RequestBody String openId){
        String unionId =wxMsgPush.getunionId(openId);
        return unionId;
    }

    @GetMapping(value = "/getcode")
    public String getcode() throws UnsupportedEncodingException {
        String code =wxMsgPush.getcode();
        return code;
    }

    @GetMapping(value = "/code")
    public String code(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] codes = parameterMap.get("code");
        if (codes!=null && codes.length>0){
            return codes[0];
        }
        return null;
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(String openid) throws WxErrorException {
        String user=wxMsgPush.getUserInfo(openid);
        return user;
    }

    @GetMapping(value = "/gettoken")
    public String gettoken(String code) throws WxErrorException {
        String user=wxMsgPush.gettoken(code);
        return user;
    }
}
