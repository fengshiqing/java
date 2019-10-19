package com.kunning.JavaSE.线程;

/**
 * <创建多线程的第二种方式：实现java.lang.Runnable接口>
 */
public class Thread_02_Runnable {

    public static void main(String[] args) {
        MyThread mt1 = new Thread_02_Runnable().new MyThread(); // 实例化对象
        MyThread mt2 = new Thread_02_Runnable().new MyThread(); // 实例化对象
        Thread t1 = new Thread(mt1); // 实例化Thread类对象
        Thread t2 = new Thread(mt2); // 实例化Thread类对象
        t1.setName("线程1");
        t2.setName("线程2");
        // 一个线程只能够执行一次start()，执行多次会抛异常
        t1.start(); // 启动多线程
        t2.start(); // 启动多线程
    }

    // 实现Runnable接口，作为线程的实现类
    class MyThread implements Runnable {
        @Override
        public void run() { // 覆写run()方法，作为线程 的操作主体
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }

}
