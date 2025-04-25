/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.threadlocal;

import org.junit.jupiter.api.Test;

/**
 * 功能描述：线程内单例。
 *
 * @author fengshiqing
 * @since 2021-05-04
 */
public class ThreadLocalSingletonTest {

    @Test
    public void testThreadLocalSingleton() {
        System.out.println(Thread.currentThread().getName() + "===" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + "===" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + "===" + ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "===" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + "===" + ThreadLocalSingleton.getInstance());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + "===" + ThreadLocalSingleton.getInstance());
                System.out.println(Thread.currentThread().getName() + "===" + ThreadLocalSingleton.getInstance());
            }
        });
        t1.start();
        t2.start();
        System.out.println("End");
    }
}
