/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton;

/**
 * 在高并发环境下，{@link Singleton_2} 会创建出多个对象。必须进行加锁处理。
 */
public class Singleton_3 {

    private static Singleton_3 single; // 1、私有静态变量

    private Singleton_3() { // 2、私有构造器
    }

    // 加了synchronized 保证并发安全，但是性能不行
    public synchronized static Singleton_3 getInstance() { // 3、公有静态方法
        if (single == null) {
            single = new Singleton_3();
        }
        return single;
    }
}
