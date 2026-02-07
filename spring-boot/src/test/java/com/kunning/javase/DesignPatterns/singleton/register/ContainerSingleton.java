/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：容器单例/注册式单例。
 * 这种做不到单例，可能会创建多个。
 *
 * @author 冯仕清
 * @since 2021-05-04
 */
public class ContainerSingleton {

    /**
     * 私有化构造器
     */
    private ContainerSingleton() {
    }

    private static final Map<String, Object> IOCMAP = new ConcurrentHashMap<>();

    public static Object getInstance(String className) {
        Object instance = null;
        if (!IOCMAP.containsKey(className)) {
            try {
                instance = Class.forName(className).newInstance();
                IOCMAP.put(className, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return instance;
        } else {
            return IOCMAP.get(className);
        }
    }

}
