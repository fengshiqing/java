/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.multithread;

import org.junit.jupiter.api.Test;

/**
 * 功能描述：可重入锁。synchronized是重入锁。
 */
public class SyncTest {

    public synchronized void sync1() { // main线程获得对象锁
        System.out.println("sync1");
        sync2(); // 如果synchronized 不是重入锁，此处就会发生死锁。
    }

    public void sync2() {
        synchronized (this) { // synchronized是重入锁，增加充入次数就行
            System.out.println("sync2");
        } // 方法执行完毕，减少重入次数。
    }

    @Test
    public void test() {
        SyncTest sync = new SyncTest();
        sync.sync1();
    }

}
