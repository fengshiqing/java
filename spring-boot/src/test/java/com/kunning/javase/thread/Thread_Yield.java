package com.kunning.javase.thread;

/**
 * Java线程中的Thread.yield()方法，译为线程让步。就是说当一个线程执行这个方法后，就会把自己的CPU执行权让出来，
 * 
 * 让自己或者其它的线程去抢CPU执行权，注意是让自己或者其他线程运行，并不一定是让给其他线程。
 * 
 * yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
 * 
 * 证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
 * 
 * 举个例子：一帮朋友在排队上公交车，轮到Yield的时候，他突然说：我不想先上去了，咱们大家来竞赛上公交车。然后所有人就一块冲向公交车，
 * 
 * 有可能是其他人先上车了，也有可能是Yield先上车了。
 * 
 * 但是线程是有优先级的，优先级越高的人，就一定能第一个上车吗？这是不一定的，优先级高的人仅仅只是第一个上车的概率大了一点而已，
 * 
 * 最终第一个上车的，也有可能是优先级最低的人。并且所谓的优先级执行，是在大量执行次数中才能体现出来的。
 *
 */
public class Thread_Yield {

	public static void main(String args[]) {
		MyThread my = new Thread_Yield().new MyThread(); // 实例化MyThread对象
		Thread t1 = new Thread(my, "线程A");
		Thread t2 = new Thread(my, "线程B");
		t1.start();
		t2.start();
	}

	class MyThread implements Runnable { // 实现Runnable接口
		@Override
		public void run() { // 覆写run()方法
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				if (i == 2) {
					System.out.println(Thread.currentThread().getName() + " - 让出线程：");
					Thread.currentThread();
					Thread.yield(); // 线程礼让
				}
				System.out.println(Thread.currentThread().getName() + "运行，i = " + i); // 取得当前线程的名字
			}
		}
	}

}
