/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.线程;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 功能描述：生产者/消费者
 *
 * @author fengshiqing
 * @since 2021-05-09
 **/
public class ProduceANDCustome {

    /**
     * 生产者
     */
    static class Producer implements Runnable {

        private Queue<String> queue;
        private int maxSize;

        private Producer(Queue<String> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            int i = 0;
            while(true){
                synchronized(queue) {
                    if (queue.size() >= maxSize) { // 此处要用while，是放了防止刚释放了锁，结果自己又给抢到了。
                        try {
                            queue.wait(); // wait一定会释放锁，
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(1000); // sleep只是释放cpu资源，没有释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    queue.add("消息---" + i++);
                    queue.notify(); // 唤醒消费者
                }
            }
        }

    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable {

        private Queue<String> queue;
        private int maxSize;

        private Consumer(Queue<String> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while(true){
                synchronized(queue) {
                    if (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("【消费者消费===】" + queue.remove());
                    queue.notify(); // 唤醒阻塞状态的生产者
                }
            }
        }

    }

    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();
        int maxSize = 5;
        Producer producer = new Producer(queue, maxSize);
        Consumer consumer = new Consumer(queue, maxSize);

        Thread t1 = new Thread(producer, "生产者");
        Thread t2 = new Thread(consumer, "消费者");
        t1.start();
        t2.start();
    }

}
