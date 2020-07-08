package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.fdds.SourceEventCopyVO;
import com.dousnl.exception.MyException;
import com.dousnl.service.UserService;
import com.dousnl.strategy.CalculateOpertionContext;
import com.dousnl.strategy.CalculateStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/30 14:54
 */
@Slf4j
@RestController
public class CalculateController {

    @Autowired
    private CalculateOpertionContext calculateOpertionContext;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/operation")
    public String calculate(@RequestParam("mode") String mode){
        CalculateStrategy calculateStrategy = calculateOpertionContext.selectStrategy(mode);
        return calculateStrategy != null ? String.valueOf(calculateStrategy.doOperation(20, 5)) : "无匹配的策略";
    }

    @GetMapping(value = "/async")
    public String async() throws InterruptedException {
        User user = userService.getUser();
        return "success";
    }

    @GetMapping(value = "/ex")
    public String ex() {
        int i=1/0;
        return "success";
    }

    @GetMapping(value = "/myex")
    public String myex() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new MyException("9999", e.getMessage());
        }
        return "success";
    }


    @PostMapping(value = "/valid")
    public String valid(@RequestBody SourceEventCopyVO vo) {
        log.info(">>>>>>>>>valid vo:{}",vo);
        vo=null;
        if (vo!=null && vo.getActivityId()>0){
            System.out.println("valid is null...");
        }
        return "success";
    }
}
