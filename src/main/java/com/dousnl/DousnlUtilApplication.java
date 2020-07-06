package com.dousnl;

import com.dousnl.service.BusyService;
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
    private BusyService busyService;

    public static void main(String[] args) {
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
        //CompletableFuture.allOf(jane,allen,james).join();
        log.info(jane.get());
        log.info(allen.get());
        log.info(james.get());
        return "success";
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String,Object>();
        redisTemplate.setConnectionFactory(factory);
        // key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
        // 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
        // 或者JdkSerializationRedisSerializer序列化方式;
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();// Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 设置值（value）的序列化采用jackson2JsonRedisSerializer。
        //具体设置什么序列化可参考 https://www.cnblogs.com/wangzhuxing/p/10198347.html

        //Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //Jackson2JsonRedisSerializer反序列化，会返回LinkedHashMap,故不用此序列化

        //redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //JdkSerializationRedisSerializer，出于性能考虑，也不用此序列化


//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

         GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
         redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
         redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

//        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
//        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
//        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

        //经过压测工具对比，GenericJackson2JsonRedisSerializer，性能最好
        return redisTemplate;
    }

}
