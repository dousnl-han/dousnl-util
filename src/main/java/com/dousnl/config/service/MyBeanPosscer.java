package com.dousnl.config.service;

import com.dousnl.config.service.MyServie;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/17 18:47
 */
@Component
public class MyBeanPosscer implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        MyServie my=new MyServie();
        beanFactory.registerSingleton(MyServie.class.getName(),my);
    }
}
