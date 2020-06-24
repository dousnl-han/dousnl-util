package com.dousnl.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/9 18:09
 */
@Component
public class UserPoccessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)){
            System.out.println("------------------------------");
            System.out.println("对象" + beanName + "开始实例化");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            System.out.println("------------------------------");
            System.out.println("对象" + beanName + "结束实例化");
        }
        return bean;
    }
}
