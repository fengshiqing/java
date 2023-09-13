package com.kunning.javase.thread;

import org.junit.jupiter.api.Test;

/**
 * 功能描述：守护线程
 *
 * @author 冯仕清
 * @since 2019/12/24
 */
public class Thread_Daemon {

    @Test
    public void test() {
        MyThread mt = new MyThread(); // 实例化Runnable子类对象
        Thread t = new Thread(mt, "线程"); // 实例化Thread对象
        t.setDaemon(true); // 此线程在后台运行
        t.start(); // 启动线程
        System.out.println("主线程执行完毕");
    }

    static class MyThread implements Runnable { // 实现Runnable接口
        @Override
        public void run() { // 覆写run()方法
            while (true) {
                System.out.println(Thread.currentThread().getName() + "在运行。");
            }
//			System.out.println("守护进程结束");
        }
    }


}
