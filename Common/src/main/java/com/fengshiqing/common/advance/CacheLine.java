/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common.advance;

/**
 * 功能描述：缓存行。
 * 对应这里的讲解：
 * <a href="https://www.bilibili.com/video/BV1PT411w7JC?p=9&vd_source=f2fb919142ce62e6571426a12817634e" />
 *
 * @author fengshiqing
 * @since 2022-10-02
 */
public class CacheLine {

    static class Padding {
        volatile long p1, p2, p3, p4, p5, p6, p7; //
    }

    static class T extends Padding {
        volatile long x = 0L; // 缓存行，不需要通知其他缓存行数据过期了，速度自然快。
    }

    private static final T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (long i=0; i<1000_0000; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i=0; i<1000_0000; i++) {
                arr[1].x = i;
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - start);
    }

}
