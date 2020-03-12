package com.dousnl;

import com.dousnl.service.BusyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/12 13:53
 */
@Slf4j
@RestController
@SpringBootApplication
public class DousnlUtilApplication {
    @Autowired
    private BusyService busyService;

    public static void main(String[] args) {
        SpringApplication.run(DousnlUtilApplication.class, args);
    }

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("synce1--");
        executor.initialize();
        return executor;
    }

    @RequestMapping("/test")
    public String test() throws InterruptedException, ExecutionException {
        CompletableFuture<String> jane = busyService.busyMethod("Jane");
        CompletableFuture<String> allen = busyService.busyMethod("Allen");
        CompletableFuture<String> james = busyService.busyMethod("James");
        //CompletableFuture.allOf(jane,allen,james).join();
        log.info(jane.get());
        log.info(allen.get());
        log.info(james.get());
        return "success";
    }
}
