package com.kunning.javase.线程;

import org.junit.Test;

/**
 * 功能描述：创建多线程的第一种方式：继承java.lang.Thread类
 *
 * @author 冯仕清
 * @since 2019/12/24
 */
public class Thread_01_Thread {

    @Test
    public void test() {
        MyThread t1 = new MyThread(); // 实例化对象
        MyThread t2 = new MyThread(); // 实例化对象
        t1.setName("线程1");
        t2.setName("线程2");
        // 一个线程只能够执行一次start()，执行多次会抛异常
        t1.start(); // 调用线程主体
        t2.start(); // 调用线程主体
    }

    // 继承Thread类，作为线程的实现类
    static class MyThread extends Thread {
        @Override
        public void run() { // 覆写run()方法，作为线程 的操作主体
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }

}
