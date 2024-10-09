package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.utils.date.DateUtil;
import com.dousnl.utils.http.HttpClentUtils;
import com.dousnl.utils.http.Result;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;
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
@RestController
@RequestMapping("/chat")
public class ChatController {


    @PostMapping("/v1")
    public String chat(@RequestBody Map<String, String> params) throws IOException {

        String question = MapUtils.getString(params, "qe");

        String url = "https://api.openai.com/v1/chat/completions";
        final HashMap<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Authorization", "Bearer sk-wgqkRT7j3PJQ6SL4u9UANwcbSrc6D3CSDas6ZBoYhYtxuKr6");

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

    @PostMapping("/perplexity/v1")
    public String perplexityChatV1(@RequestBody Map<String, String> params) throws IOException {

        String question = MapUtils.getString(params, "qe");
        final String API_URL = "https://api.perplexity.ai/chat/completions";
        final String API_KEY = "your_api_key_here";


        final HashMap<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Authorization", "Bearer " + API_KEY);


        final HashMap<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("model", "mistral-7b-instruct");
        paramMap.put("messages", "[{\"role\":\"user\",\"content\":\"+" + question +"\"}]");
        paramMap.put("temperature", 0.7);
        final Result post = HttpClentUtils.postJson(API_URL, headerMap, paramMap, "utf-8");
        String body = post.getBody();
        int statusCode = post.getStatusCode();
        System.out.println(statusCode);
        System.out.println(JSON.toJSONString(body));
        return JSON.toJSONString(body);
    }

    @PostMapping("/coze/v1")
    public String cozeChatV1(@RequestBody Map<String, String> params) throws IOException {

        String question = MapUtils.getString(params, "qe");
        final String API_URL = "https://api.coze.cn/open_api/v2/chat";
        final String API_KEY = "pat_NWTkR67PO6lNzlhYrLcZt90Ks9tv6rWmhMVRARNTxLCeu8UqRjrKfs0lxWNsygSx";


        final HashMap<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Authorization", "Bearer " + API_KEY);


        final HashMap<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("bot_id", "7384676542137925641");
        paramMap.put("user", "499623785413220");
        paramMap.put("query", question);
        paramMap.put("stream", false);
        final Result post = HttpClentUtils.postJson(API_URL, headerMap, paramMap, "utf-8");
        String body = post.getBody();
        int statusCode = post.getStatusCode();
        System.out.println(statusCode);
        //System.out.println(JSON.toJSONString(body));
        return JSON.toJSONString(body);
    }

    @PostMapping("/volcengine/v1")
    public String volcengineChatV1(@RequestBody Map<String, String> params) throws IOException {

        String question = MapUtils.getString(params, "qe");
        final String API_URL = "https://visual.volcengineapi.com/";
        final String API_KEY = "pat_DlPp7h3CZFST6uoSAYGOq6wWdeb7ekRqKlKbWOABtE9XEPnchIfLGYvysgDjd1Px";


        final HashMap<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-Date", DateUtil.dateToString2(new Date()));
        headerMap.put("Authorization", "Bearer " + API_KEY);


        final HashMap<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("bot_id", "7384676542137925641");
        paramMap.put("user", "29032201862555");
        paramMap.put("query", question);
        paramMap.put("stream", false);
        final Result post = HttpClentUtils.postJson(API_URL, headerMap, paramMap, "utf-8");
        String body = post.getBody();
        int statusCode = post.getStatusCode();
        System.out.println(statusCode);
        System.out.println(JSON.toJSONString(body));
        return JSON.toJSONString(body);
    }


}
