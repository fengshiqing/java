package com.kunning.JavaSE.线程_练习;

class PrintNumThread implements Runnable {

	int num = 0;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				super.notify();
				if (num < 100) {
					System.out.println(Thread.currentThread().getName() + ":" + num);
					num++;
				} else {
					break;
				}
				try {
					super.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

public class test {

	public static void main(String[] args) {
		PrintNumThread printNum = new PrintNumThread();
		Thread t1 = new Thread(printNum);
		Thread t2 = new Thread(printNum);

		t1.setName("111");
		t2.setName("222222");

		t1.start();
		t2.start();
	}

}
