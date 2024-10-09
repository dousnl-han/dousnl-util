package com.dousnl.controller;


import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.lang.reflect.Parameter;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/7/17
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/7/17       hll    新增              1001
 ********************************************************************/
public class JavaParserExample {

    public void test1(String s1, int s2) {

    }
    public void test2(String s1, int s2) {

    }

    public static void main(String[] args) throws Exception {
        StandardReflectionParameterNameDiscoverer reflectionParameterNameDiscoverer = new StandardReflectionParameterNameDiscoverer();
        final String[] test1s1 = reflectionParameterNameDiscoverer.getParameterNames(JavaParserExample.class.getMethod("test1", String.class, int.class));
        final Parameter[] test1s = JavaParserExample.class.getMethod("test1",String.class , int.class).getParameters();
        for (String parameter : test1s1) {
            System.out.println(parameter);
        }
    }
}
