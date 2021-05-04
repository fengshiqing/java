package com.kunning.javase.线程;

import org.junit.jupiter.api.Test;

/**
 * <线程的休眠>
 */
public class ThreadSleepDemo {

    @Test
    public void test() {
        MyThread mt = new MyThread(); // 实例化Runnable子类对象
        Thread t = new Thread(mt, "线程"); // 实例化Thread对象
        t.start(); // 启动线程
    }

    static class MyThread implements Runnable { // 实现Runnable接口
        @Override
        public void run() { // 覆写run()方法
            for (int i = 0; i < 30; i++) {
                try {
                    Thread.sleep(1000); // 线程休眠，毫秒
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName() + "运行，i = " + i); // 取得当前线程的名字
            }
        }
    }

}
