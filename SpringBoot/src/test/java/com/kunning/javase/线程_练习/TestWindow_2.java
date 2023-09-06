package com.kunning.javase.线程_练习;

public class TestWindow_2 {

	public static void main(String[] args) {
		Window1 w = new Window1();
		Thread w1 = new Thread(w);
		Thread w2 = new Thread(w);
		Thread w3 = new Thread(w);
		
		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");
		
		w1.start();
		w2.start();
		w3.start();
	}

}

// 模拟火车站售票窗口，开启三个窗口售票，总票数100张
//使用实现的方式
class Window1 extends Thread {
	/**
	 * 用实现的方式的优点，成员变量不必是static的，生命周期不长。
	 * 每个线程中的ticket是同一个
	 */
	int ticket = 100;
	
	@Override
	public void run() {
		while(true) {
			if(ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "：售票" + ticket);
				ticket--;
			} else {
				break;
			}
		}
	}
}

