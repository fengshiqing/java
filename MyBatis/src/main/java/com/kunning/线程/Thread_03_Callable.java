package com.kunning.线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <创建多线程的第三种方式：实现java.util.concurrent.Callable接口>
 */
public class Thread_03_Callable {

    public static void main(String[] args) {
        MyThread mt1 = new Thread_03_Callable().new MyThread(); // 实例化对象
        // 执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> result = new FutureTask<>(mt1); 
        Thread t1 = new Thread(result); // 实例化Thread类对象
        t1.setName("线程1");
        // 一个线程只能够执行一次start()，执行多次会抛异常
        t1.start(); // 启动多线程
        
        // 接收线程运算后的结果
        try {
        	// FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
			int sum = result.get();
			System.out.println("主线程接收到的结果：" + sum);
			System.out.println("----------------------------------------");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    }

    // 实现Runnable接口，作为线程的实现类
    class MyThread implements Callable<Integer> {
    	int sum = 0;
		@Override
		public Integer call() throws Exception {
			for (int i = 0; i < 10; i++) {
                sum += i;
			}
			System.out.println("多线程中执行结果：" + sum);
			return sum;
		}
    }

}
