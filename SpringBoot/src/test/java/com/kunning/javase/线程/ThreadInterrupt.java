package com.kunning.javase.线程;

/**
 * <中断线程>
 */
public class ThreadInterrupt {

	/**
	 * 当一个线程运行时，另外一个线程可以直接通过interrupt()方法中断其运行状态
	 */
	public static void main(String[] args) {
		MyThread mt = new MyThread(); // 实例化Runnable子类对象
		Thread t = new Thread(mt, "线程"); // 实例化Thread对象
		t.start(); // 启动线程
		try {
			Thread.sleep(1); // 线程休眠1毫秒
		} catch (InterruptedException e) {
			System.out.println("000、休眠被终止");
		}
		t.interrupt(); // 强制中断线程执行
	}

	// 内部类，实现Runnable接口
	static class MyThread implements Runnable {
		@Override
		public void run() { // 覆写run()方法
			System.out.println("1、进入run()方法");
			System.out.println("1231654546486");
			try {
				Thread.sleep(1); // 线程休眠1毫秒
				System.out.println("2、已经完成了休眠");
			} catch (InterruptedException e) {
				System.out.println("3、休眠被终止");
				return; // 返回调用处
			}
			System.out.println("4、run()方法正常结束");
		}
	}

}
