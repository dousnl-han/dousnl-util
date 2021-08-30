package com.dousnl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/12/27 16:45
 */
@Service
@Slf4j
public class DusyService extends CommonService{

    @Autowired
    private ContstructService contstructService;

    public DusyService() {
        System.err.println(">>>>>>>>>>>DusyService>>构造方法>>"+this.toString());
    }

    @PostConstruct
    public void DP(){
        System.err.println(">>>>>>>>>>>DusyService.contstructService>>>>"+contstructService.toString());
        System.err.println(">>>>>>>>>>>DusyService>>>>"+this.toString());
    }
    //@Async
    public CompletableFuture<String> busyMethod(String name) throws InterruptedException {
        log.info(name);
        String s = "Hello,"+name+"!";
        //模拟耗时操作，5秒
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(s);
    }
}
