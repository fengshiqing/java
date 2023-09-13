package com.kunning.javase.thread;

/**
 * <线程的优先级>
 */
public class Thread_Priority {

	class MyThread implements Runnable { // 实现Runnable接口
		@Override
		public void run() { // 覆写run()方法
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(500); // 线程休眠
				} catch (InterruptedException e) {
				}
				System.out.println(Thread.currentThread().getName() + "运行，i = " + i); // 取得当前线程的名字
			}
		}
	}

	/**
	 * 在 Java 的线程操作中，所有的线程在运行前都会保持在就绪状态，那么此时，哪个线程的优先级高，哪个线程就有可能会先被执行。
	 */
	public static void main(String args[]) {
		Thread t1 = new Thread(new Thread_Priority().new MyThread(), "线程 1 "); // 实例化线程对象
		Thread t2 = new Thread(new Thread_Priority().new MyThread(), "线程 2 "); // 实例化线程对象
		Thread t3 = new Thread(new Thread_Priority().new MyThread(), "线程 3 "); // 实例化线程对象
		t1.setPriority(Thread.MIN_PRIORITY); // 设置最低优先级
		t2.setPriority(Thread.NORM_PRIORITY); // 设置中等优先级
		t3.setPriority(Thread.MAX_PRIORITY); // 设置最高优先级
		t1.start(); // 启动线程
		t2.start(); // 启动线程
		t3.start(); // 启动线程
	}

}
