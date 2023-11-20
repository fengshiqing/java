package com.kunning.javase.thread;

//线程通信。如下的三个关键字使用的话，都得在同步代码块或同步方法中。
//wait():一旦一个线程执行到wait()，就释放当前的锁。
//notify()/notifyAll():唤醒wait的一个或所有的线程
//使用两个线程打印 1-100. 线程1, 线程2 交替打印
public class WaitAndNotifyTest {

    public static void main(String[] args) {
        PrintNum p = new PrintNum();
        Thread t1 = new Thread(p, "甲");
        Thread t2 = new Thread(p, "乙");

        t1.start();
        t2.start();
    }


    static class PrintNum implements Runnable {
        int num = 1;

        final Object obj = new Object();


        /**
         * 两个线程交替的、依次的打印数字：1--100
         */
        @Override
        public void run() {
            while (true) {
                synchronized (obj) {
                    obj.notify();
                    if (num <= 100) {
                        System.out.println(Thread.currentThread().getName() + ":" + num);
                        num++;
                    } else {
                        break;
                    }

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
