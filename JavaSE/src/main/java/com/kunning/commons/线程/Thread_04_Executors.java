package com.kunning.commons.线程;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：创建多线程的第四种方式：线程池
 *
 * @author 冯仕清
 * @since 2020/04/06
 */
public class Thread_04_Executors {

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 0; i < 15; i++) {
            MyThread thread = new MyThread();
            executorService.execute(thread);
            Thread.sleep(1000);
        }
        executorService.shutdown(); // 关闭线程池
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("通过线程池方式创建的线程：" + Thread.currentThread().getName());
        }
    }
}

