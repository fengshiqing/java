/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.register;

import org.junit.jupiter.api.Test;

/**
 * 功能描述：容器单例/注册式单例。
 *
 * @author fengshiqing
 * @since 2021-05-04
 */
public class ContainerSingletonTest {

    /**
     * 功能描述：测试 容器单例/注册式单例 是否线程安全
     */
    @Test
    public void testContainerSingleton() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            Object instance1 = ContainerSingleton.getInstance("com.kunning.common.bean.User");
            System.out.println(instance1.hashCode());
        });
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                Object instance1 = ContainerSingleton.getInstance("com.kunning.common.bean.User");
                System.out.println(instance1.hashCode());
            }
        };
        thread1.setName("子线程-1");
        thread1.start();
        thread2.setName("子线程-2");
        thread2.start();

        Thread.currentThread().join();
    }

}
