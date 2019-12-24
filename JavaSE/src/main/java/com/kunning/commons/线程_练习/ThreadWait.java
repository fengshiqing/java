package com.kunning.commons.线程_练习;


import org.junit.Test;

public class ThreadWait {

    public static void main(String[] args) {
        PrintNumThread printNum = new PrintNumThread();
        Thread t1 = new Thread(printNum, "线程1");
        Thread t2 = new Thread(printNum, "线程2");
        t1.start();
        t2.start();
    }

    static class PrintNumThread implements Runnable {
        int num = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    super.notify();
                    if (num < 100) {
                        System.out.println(Thread.currentThread().getName() + ":" + num);
                        num++;
                    } else {
                        break;
                    }
                    try {
                        super.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
