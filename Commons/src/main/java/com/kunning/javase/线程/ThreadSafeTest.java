package com.kunning.javase.线程;

//线程加锁。
public class ThreadSafeTest implements Runnable {

    int num = 10;

    public void run() {
        long name = Thread.currentThread().getId();
        while (num > 0) {
            synchronized (this) {// 不加这个锁的话，四个线程会同时执行这段程序，导致num值发生未知错误。
                if (num > 0) {
                    try {
                        Thread.sleep(1000);// 休眠1秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程 " + name + " 剩余票数为：" + --num);
                }
            }
        }
        System.out.println("线程" + name + "票卖完了");
    }

    public static void main(String[] args) {
        long name = Thread.currentThread().getId();
        ThreadSafeTest t = new ThreadSafeTest();
        Thread tA = new Thread(t);
        Thread tB = new Thread(t);
        Thread tC = new Thread(t);
        Thread tD = new Thread(t);
        tA.start();
        tB.start();
        tC.start();
        tD.start();
        tA.run();
        tB.run();
        System.out.println("线程 " + name);
        tC.run();
        tD.run();
        System.out.println("结束");
    }

}
