/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.thread;

/**
 * 生产者/消费者问题
 * 生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员会叫生产者停一下，
 * 如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，
 * 如果店中有产品了再通知消费者来取走产品。
 * 分析：
 * 这里可能出现两个问题：
 * 生产者比消费者快时，消费者会漏掉一些数据没有取到。
 * 消费者比生产者快时，消费者会取相同的数据。
 * 1、是否涉及到多线程的问问题？是！生产者、消费者
 * 2、是否涉及到共享数据？有，要考虑线程安全
 * 3、共享数据是谁？是产品的数量
 * 4、是否涉及到线程的通信？存在生产者和消费者的通信。
 */
public class ProducerANDCustomer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();// 共享数据
        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);

        Thread t1 = new Thread(p1, "生产者1");
        Thread t3 = new Thread(p1, "生产者2");
        Thread t2 = new Thread(c1, "消费者");

        t1.start();
        t3.start();
        t2.start();
    }

    // 店员
    static class Clerk {
        private int productNum;

        // 由店员控制继续生产、停止生产
        public synchronized void addProduct() {
            if (productNum >= 20) {
                try {
                    this.wait();// 产品太多，让生产者停止生产
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                productNum++;
                System.out.println(Thread.currentThread().getName() + "：生产了第 " + productNum + " 个产品");
                this.notify();// 通知继续消费
            }
        }

        // 由店员控制消费、等待
        public synchronized void consumeProduct() {
            if (productNum <= 0) {
                try {
                    this.wait();// 产品不够，让消费者等一下
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "：消费了第 " + productNum + " 个产品");
                productNum--;
                this.notifyAll();// 通知继续生产
            }
        }
    }

    // 生产者
    static class Producer implements Runnable {
        private final Clerk clerk;

        public Producer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            System.out.println("生产者开始生产");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.addProduct();
            }
        }
    }

    // 消费者
    static class Consumer implements Runnable {
        private final Clerk clerk;

        public Consumer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            System.out.println("消费者开始消费");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.consumeProduct();
            }
        }
    }

}
