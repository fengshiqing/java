package com.kunning.javase.thread;

public class TestWindow_1 {

	public static void main(String[] args) {
		Window w1 = new Window();
		Window w2 = new Window();
		Window w3 = new Window();
		
		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");
		
		w1.start();
		w2.start();
		w3.start();
	}

}

// 模拟火车站售票窗口，开启三个窗口售票，总票数100张
// 使用继承的方式

/**
 * 哪种方式好？
 * 实现由于继承
 * 因为：
 * 1、避免了java单继承的局限性
 * 2、如果多个线程要操作同一份资源/数据，更合适使用实现的方式
 *
 */

class Window extends Thread {
	/**
	 * 用继承的方式的缺点，成员变量是static的，生命周期太长，
	 * 只有当前类不用了，才会销毁这个变量，不太好
	 */
	static int ticket = 100;
	
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

