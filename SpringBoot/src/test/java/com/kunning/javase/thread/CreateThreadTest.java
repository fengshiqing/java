package com.kunning.javase.thread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：创建多线程的四种方式
 *
 * @author 冯仕清
 * @since 2019/12/24
 */
public class CreateThreadTest {

    // 方式一：继承java.lang.Thread类，作为线程的实现类
    @Test
    public void test1() {
        class MyThread1 extends Thread {
            @Override
            public void run() { // 覆写run()方法，作为线程 的操作主体
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": i = " + i);
                }
            }
        }

        MyThread1 t1 = new MyThread1(); // 实例化对象
        MyThread1 t2 = new MyThread1(); // 实例化对象
        t1.setName("线程1");
        t2.setName("线程2");
        // 一个线程只能够执行一次start()，执行多次会抛异常
        t1.start(); // 调用线程主体
        t2.start(); // 调用线程主体
    }

    // 方式二：实现java.lang.Runnable接口，作为线程的实现类
    @Test
    public void test2() {
        class MyThread2 implements Runnable {
            @Override
            public void run() { // 覆写run()方法，作为线程 的操作主体
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": i = " + i);
                }
            }
        }
        MyThread2 mt1 = new MyThread2(); // 实例化对象
        MyThread2 mt2 = new MyThread2(); // 实例化对象
        Thread t1 = new Thread(mt1, "线程1"); // 实例化Thread类对象
        Thread t2 = new Thread(mt2, "线程2"); // 实例化Thread类对象
        // 一个线程只能够执行一次start()，执行多次会抛异常
        t1.start(); // 启动多线程
        t2.start(); // 启动多线程
    }

    // 方式三：实现java.util.concurrent.Callable接口，作为线程的实现类
    @Test
    public void test3() {
        class MyThread3 implements Callable<Integer> {
            int sum = 0;

            @Override
            public Integer call() {
                for (int i = 0; i < 10; i++) {
                    sum += i;
                }
                System.out.println("【多线程中执行结果：】" + sum);
                return sum;
            }
        }
        MyThread3 mt1 = new MyThread3(); // 实例化对象
        FutureTask<Integer> result = new FutureTask<>(mt1); // 执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        Thread t1 = new Thread(result, "线程1"); // 实例化Thread类对象

        t1.start(); // 启动多线程

        // 接收线程运算后的结果
        try {
            // FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
            int sum = result.get();
            System.out.println("【主线程接收到的结果：】" + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // 方式四：创建多线程的第四种方式：线程池
    @Test
    public void test4() throws InterruptedException {
        class MyThread4 implements Runnable {
            @Override
            public void run() {
                System.out.println("通过线程池方式创建的线程：" + Thread.currentThread().getName());
            }
        }


        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 0; i < 15; i++) {
            MyThread4 thread = new MyThread4();
            executorService.execute(thread);
            Thread.sleep(1000);
        }
        executorService.shutdown(); // 关闭线程池
    }

    /**
     * 为什么要用线程池呢，咱们可以做个测试，用new10万个线程，和线程池中的线程来做个测试
     * <p>
     * 问：下面testThread、testThreadPool哪种方式快？
     * 答：testThreadPool快，因为new线程会消耗资源。线程池是直接拿来用，省去创建销毁线程的操作。
     */
    @Test
    public void testThread() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            t1.start();
            t1.join();
        }
        System.out.println("【时间：】" + (System.currentTimeMillis() - startTime)); // 【时间：】15966
        System.out.println("【大小：】" + list.size()); // 【大小：】100000
    }

    @Test
    public void testThreadPool() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        executorService.shutdown();
        System.out.println("【时间：】" + (System.currentTimeMillis() - startTime)); // 【时间：】62
        System.out.println("【大小：】" + list.size()); // 【大小：】57311
    }

    /**
     * JDK API中给定的线程池的缺点、以及自定义线程池
     */
    @Test
    public void testExecutorService() {
        class MyTask implements Runnable {
            final int i;

            public MyTask(int i) {
                this.i = i;
            }

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " -- " + i);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        ExecutorService execService1 = Executors.newCachedThreadPool(); // 阿里巴巴手册不推荐使用，会因为创建无限制的线程导致CPU 100%
        ExecutorService execService2 = Executors.newFixedThreadPool(10); // 阿里巴巴手册不推荐使用，会因为阻塞队列无限制而导致OOM
        ExecutorService execService3 = Executors.newSingleThreadExecutor(); // 阿里巴巴手册不推荐使用，会因为阻塞队列无限制而导致OOM

        // 自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        // 上面这个线程池有什么问题吗？  有的，会报线程拒绝异常，在第31个线程到来时发生异常，为什么是31个呢？因为第二次参数表示的是总线程数，包含了核心线程数的。
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new MyTask(i));
        }
        // 问：哪种执行最快
        // 答：第一种，因为最大可以用 Integer.MAX_VALUE 个线程，其次是 第二种，固定10个线程，最后是第三种，只有一个线程

        // 还有一个知识点：
        // 提交优先级：先核心线程、其次阻塞队列、其次非核心线程
        // 执行优先级：先核心线程、其次非核心线程、其次阻塞队列
    }


}
