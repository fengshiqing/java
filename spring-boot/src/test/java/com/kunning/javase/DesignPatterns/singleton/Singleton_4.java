/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton;

/**
 * Singleton_3的方式创建单例是没有并发问题的，不过有个缺点：
 * 问题1：整个方法都加锁，有些业务代码不需要加锁；
 * 问题2：每一次调用都需要同步，实际上只有第一次创建对象时才需要同步，以后每次都是获取对象，不需要加锁，此时同步都是一种累赘，降低效率
 * <p>
 * 优化方式：同步方法 改为 同步代码块。
 */
public class Singleton_4 {

    private volatile static Singleton_4 single; // 1、私有静态变量

    private Singleton_4() { // 2、私有构造器
    }

    public static Singleton_4 getInstance() { // 3、公有静态方法
        if (single == null) {
            synchronized (Singleton_4.class) { // 同步代码块，但是这种方式有线程并发问题，需要进一步处理
                single = new Singleton_4();
            }
        }
        return single;
    }
}

// A、B线程同时执行时，假如A线程 执行到 22行代码，判断为true，然后去获取锁，在没获取到锁时，CPU转去执行B线程
// B线程 执行到 22行代码，判断也为true，然后获取到了锁，并加锁执行完创建对象的操作后，释放锁，
// 此时，A线程 再次获取到CPU执行权，然后又去获取锁，又创建了一个对象，这个就创建了两个实例了
