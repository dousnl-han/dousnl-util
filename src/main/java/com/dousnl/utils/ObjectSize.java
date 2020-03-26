package com.dousnl.utils;

import java.lang.instrument.Instrumentation;

/**
 * 计算对象大小
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/26 10:39
 */
public class ObjectSize {
    private static volatile Instrumentation instru;

    public static void premain(String args, Instrumentation inst) {
        instru = inst;
    }

    public static Long getSizeOf(Object object) {
        if (instru == null) {
            throw new IllegalStateException("Instrumentation is null");
        }
        return instru.getObjectSize(object);
    }
}
