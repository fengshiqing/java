package com.kunning.javase.thread;

import java.util.concurrent.TimeUnit;

public class Thread_Join {

    static int value = 1;


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            value = 10;
            System.out.println("【子线程】" + value);
        });
        t1.start();

        System.out.println("【线程是否存活】" + t1.isAlive());
        // 让主线程等待 子线程t1 执行完之后，主线程再继续执行。无论子线程执行多久，都会等到子线程执行完，在执行后面的代码。
        t1.join();
        System.out.println("【线程是否存活】" + t1.isAlive());
        t1.join(2000); // 只等待两秒

        System.out.println("【主线程】" + value);
    }
}
