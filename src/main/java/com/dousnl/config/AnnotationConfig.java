package com.dousnl.config;

import com.dousnl.domain.User;
import com.dousnl.domain.UserPoccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/9 17:01
 */
@Configuration
@ComponentScan({"com.dousnl.config.service"})
public class AnnotationConfig {

    @Bean
    public User user(){
        return new User();
    }

    @Bean
    public UserPoccessor userPoccessor(){
        return new UserPoccessor();
    }

}
