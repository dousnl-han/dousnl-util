package com.dousnl.controller;

import com.dousnl.component.WxErrorException;
import com.dousnl.domain.User;
import com.factories.FactoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/9/17
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/9/17       hanliangliang     新增              1001
 ********************************************************************/
@Slf4j
@RestController
@RequestMapping("/facories")
public class FactoriesController {

    @Autowired
    private FactoriesService factoriesService;


    @PostMapping(value = "/test")
    public String userInfo(@RequestBody User user) throws WxErrorException {
        return factoriesService.factories(user);
    }

    @PostMapping(value = "/test1")
    public String userInfo1(Integer userId, Date completeTime) throws WxErrorException {
        return factoriesService.factories(1);
    }

}
