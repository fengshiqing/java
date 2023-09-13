package com.kunning.javase.thread;

// https://blog.csdn.net/c10wtiybq1ye3/article/details/78212045
public class SyncDemo02 {

	class MyThread implements Runnable {
		private int ticket = 5; // 假设一共有5张票

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				synchronized (this) { // 要对当前对象进行同步。可以把同步去掉，然后看看运行结果
					if (ticket > 0) { // 还有票
						try {
							Thread.sleep(500); // 加入延迟
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("卖票：ticket = " + ticket--);
					}
				}
			}
		}
	}

	public static void main(String args[]) {
		MyThread mt = new SyncDemo02().new MyThread(); // 定义线程对象
		Thread t1 = new Thread(mt); // 定义Thread对象
		Thread t2 = new Thread(mt); // 定义Thread对象
		Thread t3 = new Thread(mt); // 定义Thread对象
		t1.start();
		t2.start();
		t3.start();
	}

}
