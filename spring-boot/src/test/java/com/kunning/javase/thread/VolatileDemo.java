/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.thread;

/**
 * 功能描述：volatile内存可见性的分析。
 *
 * @author 冯仕清
 * @since 2021-05-09
 **/
public class VolatileDemo {

    // volatile 关键字没加上时，程序不会停止，加上 volatile 后，内存可见性没问题了，程序就能正常停止
    static volatile boolean bool = true;

    public static void main(String[] args) {

        new Thread(() -> {
            long i = 0L;
            while (bool) {
                i++;
            }
            System.out.println("【如果bool值变为false，内存是有可见性的，那么就会打印这句话，此时的i=】" + i);
        }, "子线程").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bool = false; // bool值被置为false后，线程还是一直在运行，说明可见性是有问题的，子线程看不到bool值的变化。
    }

}
