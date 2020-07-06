package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.service.UserService;
import com.dousnl.strategy.CalculateOpertionContext;
import com.dousnl.strategy.CalculateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/30 14:54
 */
@RestController
public class CalculateController {

    @Autowired
    private CalculateOpertionContext calculateOpertionContext;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/operation")
    public String calculate(@RequestParam("mode") String mode){
        CalculateStrategy calculateStrategy = calculateOpertionContext.selectStrategy(mode);
        return calculateStrategy != null ? String.valueOf(calculateStrategy.doOperation(20, 5)) : "无匹配的策略";
    }

    @GetMapping(value = "/async")
    public String async() throws InterruptedException {
        User user = userService.getUser();
        return "success";
    }
}
