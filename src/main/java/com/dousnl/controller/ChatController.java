package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.utils.http.HttpClentUtils;
import com.dousnl.utils.http.Result;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/6/24
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/6/24       hll    新增              1001
 ********************************************************************/
@Controller
@RequestMapping("/chat")
public class ChatController {


    @PostMapping("/v1")
    public String chat(@RequestBody Map<String, String> params) throws IOException {

        String question = MapUtils.getString(params, "qe");

        String url = "https://api.com/v1/chat/completions";
        final HashMap<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Content-Type", "application/json");

        final HashMap<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("model", "gpt-3.5-turbo");
        paramMap.put("messages", "[{\"role\":\"user\",\"content\":\"+" + question +"\"}]");
        paramMap.put("temperature", 0.7);
        final Result post = HttpClentUtils.postJson(url, headerMap, paramMap, "utf-8");
        String body = post.getBody();
        int statusCode = post.getStatusCode();
        System.out.println(statusCode);
        System.out.println(JSON.toJSONString(body));
        return JSON.toJSONString(body);
    }

    @PostMapping("/v2")
    public String chatV2(@RequestBody Map<String, String> params) throws IOException {

        String question = MapUtils.getString(params, "qe");

        String url = "http://gateway-api.dushu365.com/fs-chief/config/config/v100/appConfig";
        final HashMap<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("appVersion", "5.88.0");
        headerMap.put("appplt", "2");
        headerMap.put("appId", "2001");

        String param = "{\"appId\":\"2001\",\"token\":\"20240615s5MSb9a9VL3woJCTLjh\"}";
        System.out.println(JSON.toJSONString(params));
        final Result post = HttpClentUtils.postJson(url, headerMap, param, "utf-8");
        String body = post.getBody();
        int statusCode = post.getStatusCode();
        System.out.println(statusCode);
        System.out.println(JSON.toJSONString(body));
        return JSON.toJSONString(body);
    }

}
