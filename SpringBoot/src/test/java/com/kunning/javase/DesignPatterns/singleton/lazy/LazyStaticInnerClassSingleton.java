/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.lazy;

/**
 * 功能描述：静态内部类实现的单例。
 *
 * 优点：写法优雅，利用了Java本身语法特点，性能高，避免了内存浪费,不能被反射破坏
 * 缺点：相对不易阅读
 */
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton() {
        if (LazyHolder.INSTANCE != null) {
            throw new RuntimeException("【不允许非法访问】");
        }
    }

    public static LazyStaticInnerClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }

}
