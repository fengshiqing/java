package com.kunning.javase.thread;

/**
 * <死锁>
 */
public class Thread_DeadLock implements Runnable {
	private static Zhangsan zs = new Thread_DeadLock().new Zhangsan(); // 实例化static型对象
	private static Lisi ls = new Thread_DeadLock().new Lisi(); // 实例化static型对象
	private boolean flag = false; // 声明标志位，判断那个先说话

	@Override
	public void run() { // 覆写run()方法
		if (flag) {
			synchronized (zs) { // 同步张三
				zs.say();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (ls) {
					zs.get();
				}
			}
		} else {
			synchronized (ls) {
				ls.say();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (zs) {
					ls.get();
				}
			}
		}
	}

	public static void main(String args[]) {
		Thread_DeadLock t1 = new Thread_DeadLock(); // 控制张三
		Thread_DeadLock t2 = new Thread_DeadLock(); // 控制李四
		t1.flag = true;
		t2.flag = false;
		Thread thA = new Thread(t1);
		Thread thB = new Thread(t2);
		thA.start();
		thB.start();
	}

	class Zhangsan { // 定义张三类
		public void say() {
			System.out.println("张三对李四说：“你给我画，我就把书给你。”");
		}

		public void get() {
			System.out.println("张三得到画了。");
		}
	}

	class Lisi { // 定义李四类
		public void say() {
			System.out.println("李四对张三说：“你给我书，我就把画给你”");
		}

		public void get() {
			System.out.println("李四得到书了。");
		}
	}
}
