package com.kunning.javase.thread;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：线程状态：共6种。
 * <a href="https://www.bilibili.com/video/BV18z4y1F7my/?p=17&spm_id_from=pageDriver&vd_source=f2fb919142ce62e6571426a12817634e">...</a>
 * 1、NEW，初始状态，线程被构建，但是还没有调用start方法。
 * 2、RUNNABLE，运行状态，java线程将 操作系统中 的“就绪” 和 “运行”两种状态，统称为 运行中
 * 3、BLOCKED，阻塞状态，表示线程处于 阻塞锁
 * 4、WAITING，等待状态
 * 5、TIMED_WAITING，超时等待状态
 * 6、TERMINATED，终止状态
 */
public class Thread_State {
    // 线程的6种状态
    // NEW
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("【run方法里面的线程状态】" + Thread.currentThread().getState()); // RUNNABLE
        });
        // start前是 NEW状态，没有启动的状态
        System.out.println("【start 前的线程状态】" + t1.getState()); // NEW
        t1.start();
        // start后是 RUNNABLE状态，启动的状态
        System.out.println("【start 后的线程状态】" + t1.getState()); // RUNNABLE
        TimeUnit.SECONDS.sleep(3);
        System.out.println("【start 后 3秒后的线程状态】" + t1.getState()); // TERMINATED


        Table table = new Table();
        Thread student1 = new Thread(() ->{
            table.use();
        }, "s1");

        Thread student2 = new Thread(() ->{
            table.use();
        }, "s2");

        student1.start();
        Thread.sleep(1000);
        student2.start();
        Thread.sleep(1000);
        System.out.println("【验证 BLOCKED 线程状态】" + student2.getState()); // BLOCKED
    }

    static class Table {
        public synchronized void use() {
            System.out.println(Thread.currentThread().getName() + " - 使用桌子");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " - 就餐结束");
        }
    }
}
