package com.dousnl;

import com.alibaba.fastjson.JSON;
import com.dousnl.service.DusyService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;
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
    private DusyService busyService;

    public static void main(String[] args) {

//        if (args.length == 0) {
//            args = new String[] {"--server.port=8081"};
//        }
//        for (String arg : args){
//            System.out.println(arg);
//        }
//        final String property = System.getProperty("dubbo.config-center.address");
//        System.out.println(property);
        SpringApplication.run(DousnlUtilApplication.class, args);
    }

//    @Bean
//    public Executor taskExecutor(){
//        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(3);
//        executor.setQueueCapacity(500);
//        executor.setThreadNamePrefix("synce1--");
//        executor.initialize();
//        return executor;
//    }

    @RequestMapping("/test")
    public String test() throws InterruptedException, ExecutionException {
        CompletableFuture<String> jane = busyService.busyMethod("Jane");
        CompletableFuture<String> allen = busyService.busyMethod("Allen");
        CompletableFuture<String> james = busyService.busyMethod("James");
        CompletableFuture.allOf(jane,allen,james).join();
        log.info(jane.get());
        log.info(allen.get());
        log.info(james.get());
        return "success";
    }


}
