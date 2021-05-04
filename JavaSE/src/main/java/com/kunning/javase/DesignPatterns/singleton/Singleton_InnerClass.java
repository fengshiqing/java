/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.DesignPatterns.singleton;

/**
 * 单例模式：内部类实现方式。
 *
 * 优点：线程安全、性能高、优雅已读、不能被反射破坏。
 * 缺点：反射一样可以new出多个对象。
 */
public class Singleton_InnerClass {

    /**
     * 私有化构造器
     */
    private Singleton_InnerClass() {
        // 为了防止反射new出多个实例，需要如下处理
        if(Singleton_InnerClass.LazyHolder.INSTANCE != null){
            throw new RuntimeException("不允许非法访问");
        }
    }

    private Singleton_InnerClass getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final Singleton_InnerClass INSTANCE = new Singleton_InnerClass();
    }

}
