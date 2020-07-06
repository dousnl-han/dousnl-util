package com.dousnl.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/6 16:09
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("synce1--");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }

    /**
     * 处理异步方法中未捕获的异常
     */
    class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            System.out.println("Exception message - " +throwable.getMessage());
            System.out.println("Method name - " + method.getName());
            System.out.println("Parameter values - " + Arrays.toString(objects));
            log.error("error...",throwable);
        }
    }
}
