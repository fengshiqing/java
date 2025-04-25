/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 功能描述：生产者/消费者
 *
 * @author fengshiqing
 * @since 2021-05-09
 **/
public class ProduceANDCustome {

    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();
        int maxSize = 5;
        Producer producer = new Producer(queue, maxSize);
        Consumer consumer = new Consumer(queue);

        Thread t1 = new Thread(producer, "生产者");
        Thread t2 = new Thread(consumer, "消费者");
        t1.start();
        t2.start();
    }

    /**
     * 生产者
     */
    static class Producer implements Runnable {

        private final Queue<String> queue;
        private final int maxSize;

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
                            System.out.println("【生产者 wait】");
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

                    System.out.println("【生产者 生产】" + i);
                    queue.add("---消息-" + i++);
                    queue.notify(); // 唤醒消费者
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable {

        private final Queue<String> queue;

        private Consumer(Queue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true){
                synchronized(queue) {
                    if (queue.isEmpty()) {
                        try {
                            System.out.println("【没有东西可以消费了，消费者wait】");
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

                    System.out.println("【消费者消费】---消息-" + queue.remove());
                    System.out.println("【消费后，唤醒生产者继续生产】");
                    queue.notify(); // 唤醒阻塞状态的生产者
                }
            }
        }
    }

}
