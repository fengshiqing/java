/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common.advance;

import java.io.IOException;

/**
 * 功能描述：volatile 内存可见性
 *
 * @author 冯仕清
 * @since 2101/12/25
 */
public class VolatileTest {

    private static boolean running = true;

    private static void m() {
        System.out.println("m start");
        while (running) {
            // System.out.println(); // 加上这行代码，即使去掉 volatile 也有可能触发内存和缓存的同步。println方法里面用到了 synchronized， synchronized本身就会触发内存呵呵缓存的同步
        }
        System.out.println("m end");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(VolatileTest::m, "t1").start();

        // new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         VolatileTest.m();
        //     }
        // }, "t2").start();

        Thread.sleep(1000);

        running = false;

        System.in.read();
    }

}
