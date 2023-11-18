package com.kunning.javase.thread;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：守护线程
 *
 * @author 冯仕清
 * @since 2019/12/24
 */
public class Thread_Daemon {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i<100; i++) {
                try {
                    Thread.sleep(1000);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 此线程为守护线程，此行代码注释的话，会等到子线程执行完，加上这行代码，会立即结束。
        t1.setDaemon(true); // 应用场景：1、JDK的垃圾回收器；2、tomcat接收处理外部请求的线程也是守护线程。
        t1.start();

        System.out.println("【线程是否存活】" + t1.isAlive());
        System.out.println("【主线程结束】");
    }
}
