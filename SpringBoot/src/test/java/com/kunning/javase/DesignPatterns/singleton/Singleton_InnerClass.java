/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton;

/**
 * 单例模式：内部类实现方式。
 * <p>
 * 优点：线程安全、性能高、优雅已读、不能被反射破坏。
 * 缺点：反射一样可以new出多个对象，需要对私有构造器做进一步的处理。
 */
public class Singleton_InnerClass {

    /**
     * 私有化构造器
     */
    private Singleton_InnerClass() {
        // 为了防止反射new出多个实例，需要如下处理，不过这么处理后，代码就不够优雅了
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
