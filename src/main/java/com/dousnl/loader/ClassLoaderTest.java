package com.dousnl.loader;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/1 17:37
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //numeration<URL> resources = classLoader.getResources("META-INFO/spring.factory");
        Enumeration<URL> resources = classLoader.getResources("E:\\myworkspace\\mygithub\\dousnl-util\\META-INFO\\spring.factory");
        if (resources.hasMoreElements()){
            URL url = resources.nextElement();
            Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
            String factoryClassNames = properties.getProperty("org.springframework.boot.env.PropertySourceLoader");
            System.out.println(factoryClassNames.toString());
        }
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        // 这里的 10 相当于初始值
        int sum = numbers
                .stream()
                .reduce(10, Integer::sum);
        System.out.println(sum);

    }
}
