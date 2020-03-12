package com.dousnl.utils.beans;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/18 18:30
 */
public class BeanCopyUtil {
    public BeanCopyUtil() {
    }

    public static <T> void beanCopy(T source, T target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static <T> void beanCopyWithIngore(T source, T target, String... ignoreProperties) {
        String[] pns = getNullAndIgnorePropertyNames(source, ignoreProperties);
        BeanUtils.copyProperties(source, target, pns);
    }

    public static String[] getNullAndIgnorePropertyNames(Object source, String... ignoreProperties) {
        Set<String> emptyNames = getNullPropertyNameSet(source);
        String[] result = ignoreProperties;
        int var4 = ignoreProperties.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String s = result[var5];
            emptyNames.add(s);
        }

        result = new String[emptyNames.size()];
        return (String[])emptyNames.toArray(result);
    }

    public static String[] getNullPropertyNames(Object source) {
        Set<String> emptyNames = getNullPropertyNameSet(source);
        String[] result = new String[emptyNames.size()];
        return (String[])emptyNames.toArray(result);
    }

    public static Set<String> getNullPropertyNameSet(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        PropertyDescriptor[] var4 = pds;
        int var5 = pds.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        return emptyNames;
    }
}
