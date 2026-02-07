/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.multithread;

import org.junit.jupiter.api.Test;

/**
 * 功能描述：死锁，模拟死锁的发生条件。
 * 死锁四大必要条件（只要打破了其中一个，就能破坏死锁）：
 * - 互斥
 * - 请求与保持
 * - 不可被剥夺
 * - 循环等待
 *
 * @author 冯仕清
 * @since 2019-05-09
 **/
public class DeadLock {
	
	private static final Object o1 = new Object();// 创建模拟死锁的对象
	private static final Object o2 = new Object();// 创建模拟死锁的对象

	private static class Run1 implements Runnable { //内部类
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			synchronized(o1) {
				try {
					Thread.sleep(1000);// 线程休眠1秒钟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName + "进入同步块o1，准备进入o2");
				synchronized (o2) {
					System.out.println(threadName + "已经进入同步块o2");
				}
			}
		}
	}
	
	private static class Run2 implements Runnable { //内部类
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			synchronized(o2) {
				try {
					Thread.sleep(1000);// 线程休眠1秒钟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName + "进入同步块o2，准备进入o1");
				synchronized (o1) {
					System.out.println(threadName + "已经进入同步块o1");//注释调这一段代码就不会发生死锁
				}
			}
		}
	}
	
	@Test
	public static void main(String[] args) {
		Thread thread1 = new Thread(new Run1());
		Thread thread2 = new Thread(new Run2());
		thread1.start();
		thread2.start();
		/*
		 * 运行这个程序，打印结果为
		 * Thread-1进入同步块o2，准备进入o1
		 * Thread-0进入同步块o1，准备进入o2
		 * 
		 * 说明19行和37行这两个地方发生了死锁。
		 * Thread-0在等待Thread-1，而Thread-1又在等待Thread-0，这样就造成了死锁。
		 * 
		 */
	}
}
