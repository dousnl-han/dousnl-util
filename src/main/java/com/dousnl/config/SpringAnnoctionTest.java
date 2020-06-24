package com.dousnl.config;

import com.dousnl.config.service.MyServie;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/9 16:59
 */

public class SpringAnnoctionTest {

    @Test
    public void test(){

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AnnotationConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name:beanDefinitionNames){
            System.out.println("name:"+name+">>>"+context.getBean(name));
        }
        Object user = context.getBean("user");
        MyServie bean = context.getBean(MyServie.class);
        //System.out.println("user"+user);
        System.out.println(bean);
    }

}
