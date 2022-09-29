package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.AdviceCanel;
import com.dousnl.file.ReadFileSendPostRequestUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.*;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/1/27
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/1/27       hanliangliang     新增              1001
 ********************************************************************/
@RestController
@RequestMapping("/req")
public class SendPostRequestController {

    @Autowired
    private ReadFileSendPostRequestUtil readFileSendPostRequestUtil;

    @GetMapping("/post")
    public void get(AdviceCanel adviceCanel) throws IOException {
        readFileSendPostRequestUtil.sendPostReq();
    }

    @PostMapping("/post")
    public String post(@RequestBody @Valid Send adviceCanel){
        System.out.println(JSON.toJSONString(adviceCanel));
       return JSON.toJSONString(adviceCanel);
    }

}
@Data
@NoArgsConstructor
class Send{

    private Integer age;
    private String name;
}
