/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.multithread;

import org.junit.jupiter.api.Test;

/**
 * <线程的强制运行>
 */
public class ThreadJoinTest {

	static class MyThread implements Runnable { // 实现Runnable接口
		@Override
		public void run() { // 覆写run()方法
			for (int i = 0; i < 30; i++) {
				System.out.println(Thread.currentThread().getName() + "运行，i = " + i); // 取得当前线程的名字
			}
		}
	}

	@Test
	public void test() throws InterruptedException {
		MyThread mt = new MyThread(); // 实例化Runnable子类对象
		Thread t = new Thread(mt, "线程"); // 实例化Thread对象
		t.start(); // 启动线程
		for (int i = 0; i < 30; i++) {
			if (i > 10) {
				t.join(); // t线程强制运行，其他线程在此期间无法运行，必须等待此线程完成之后才可以继续执行。
			}
			System.out.println("Main线程运行 ---------> " + i);
		}
	}

}
