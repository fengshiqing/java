package 线程;

/**
 * <创建多线程的第一种方式：继承java.lang.Thread类>
 */
public class Thread_01_Thread {

	class MyThread extends Thread { // 继承Thread类，作为线程的实现类
		private String name; // 表示线程的名称

		// 构造函数。可以给线程传入一些参数。
		public MyThread(String name) {
			this.name = name; // 通过构造方法配置name属性
		}

		@Override
		public void run() { // 覆写run()方法，作为线程 的操作主体
			for (int i = 0; i < 10; i++) {
				System.out.println(name + "运行，i = " + i);
			}
		}
	}

	public static void main(String args[]) {
		System.out.println(Thread.currentThread().getName());// 获取当前线程的名字
		MyThread mt1 = new Thread_01_Thread().new MyThread("线程A "); // 实例化对象
		MyThread mt2 = new Thread_01_Thread().new MyThread("线程B "); // 实例化对象
		// 一个线程只能够执行一次start()，执行多次会抛异常
		mt1.start(); // 调用线程主体
		mt2.start(); // 调用线程主体
	}

}
