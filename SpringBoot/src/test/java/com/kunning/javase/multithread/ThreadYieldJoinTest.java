/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.multithread;

import org.junit.jupiter.api.Test;

/**
 * Thread的常用方法：
 * 1.start()：启动线程使线程进入“就绪”状态，等待获取CPU执行权执行run()方法
 * 2.run():子线程要执行的代码放入run()方法中
 * 3.currentThread()：静态的，调取当前的线程
 * 4.getName():获取此线程的名字
 * 5.setName():设置此线程的名字
 * 6.yield():调用此方法的线程释放当前CPU的执行权
 * 7.join():在A线程中调用B线程的join()方法，表示：当执行到此方法，A线程停止执行，直至B线程执行完毕，
 * A线程再接着join()之后的代码执行
 * 8.isAlive():判断当前线程是否还存活
 * 9.sleep(long l):显式的让当前线程睡眠l毫秒
 * 10.线程通信：wait()   notify()   ()
 *
 * getPriority() ：返回线程优先值 ，优先级设置为10最高的，不表示一定是此线程先执行完再执行其他的，优先级高只表示抢到cpu执行权的概率变大。
 * setPriority(int newPriority) ：改变线程的优先级
 */
public class ThreadYieldJoinTest {

    /**
     * 静态内部类
     */
    static class SubThread extends Thread {
        public SubThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("【子线程：】" + Thread.currentThread().getName() + ":" + i);
                Thread.yield();// 当前线程(子线程)释放CPU执行权。为啥让出了线程每次都是子线程抢到执行权？
            }
        }
    }

    @Test
    public void test() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        SubThread st1 = new SubThread("子线程");
        st1.setPriority(Thread.MIN_PRIORITY); // 给子线程赋予最高优先级
        st1.start();
        Thread.currentThread().setName("========主线程");
        for (int i = 1; i <= 100; i++) {
            System.out.println("【主线程：】" + Thread.currentThread().getName() + ":" + i);
            if (i % 10 == 0) {
                Thread.yield(); // 当前线程(主线程)，让出cpu使用权，下一次cpu的使用权就看谁能抢得到了，有可能是主线程，也有可能是子线程。
            }
            if (i == 20) {
                try {
                    st1.join();// 表示st1插队了，先执行了。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(st1.isAlive());
    }

}
