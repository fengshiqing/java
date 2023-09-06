/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.lazy;

/**
 * 功能描述：懒汉式单例，双重检查加锁。
 *
 * 优点:性能高了，线程安全了
 * 缺点：可读性难度加大，不够优雅————这个不算缺点吧
 */
public class LazyDoubleCheckSingleton {
    private static volatile LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {
        if (instance != null) {
            throw new RuntimeException("【不允许非法访问111】");
        }
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (instance == null) { // 检查是否要阻塞
            synchronized (LazyDoubleCheckSingleton.class) {
                // 检查是否要重新创建实例
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                    // 指令重排序的问题
                }
            }
        }
        return instance;
    }
}
