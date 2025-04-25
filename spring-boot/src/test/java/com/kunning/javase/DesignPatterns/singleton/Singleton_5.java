/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton;

/**
 * 解决Singleton_4的 并发问题。要注意 volatile。
 * <p>
 * 终极版本，这个会了可以吹牛逼了。
 */
// 可以再看看这个博客：
// https://www.cnblogs.com/zhaoyan001/p/6365064.html
public class Singleton_5 {

    private volatile static Singleton_5 single; // 1、私有静态变量

    private Singleton_5() { // 2、私有构造器
    }

    public static Singleton_5 getInstance() { // 3、公有静态方法
        if (single == null) {
            synchronized (Singleton_5.class) { // 双重检查加锁DCL（Double Check Lock）
                if (single == null) { // 如果在A线程申请锁时，B线程已经创建了对象，volatile确保线程可见性，A线程就能知道，有别的线程已经创建了对象，就不会重复创建了
                    single = new Singleton_5(); // volatile 修饰，不会指令重排序，导致并发时别的线程拿到半初始化的对象
                }
            }
        }
        return single;
    }
}

// 到这里，延时加载单例模式才算完美，能大大减少getInstance()的时间耗费，提高性能。
// 注意：双重检查加锁不适用于1.4及更早版本的jdk

// 这里对 volatile 有个面试题，volatile 必须要加吗？为什么？请看  Singleton_volatile.java


