package com.kunning.javase.thread;

/**
 * @see https://blog.csdn.net/c10wtiybq1ye3/article/details/78212045
 */
public class SyncDemo03 {

	class MyThread implements Runnable {
		private int ticket = 5; // 假设一共有5张票

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				this.sale(); // 调用同步方法
			}
		}

		// 除了可以将需要的代码设置成同步代码块外，也可以使用 synchronized 关键字将一个方法声明为同步方法。
		public synchronized void sale() { // 声明同步方法
			if (ticket > 0) { // 还有票
				try {
					Thread.sleep(3000); // 加入延迟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("卖票：ticket = " + ticket--);
			}

		}
	}

	public static void main(String args[]) {
		MyThread mt = new SyncDemo03().new MyThread(); // 定义线程对象
		Thread t1 = new Thread(mt); // 定义Thread对象
		Thread t2 = new Thread(mt); // 定义Thread对象
		Thread t3 = new Thread(mt); // 定义Thread对象
		t1.start();
		t2.start();
		t3.start();
	}

}
