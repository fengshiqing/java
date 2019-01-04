
/**
 * @see https://blog.csdn.net/c10wtiybq1ye3/article/details/78212045
 */
public class Runnable01 {

	class MyThread implements Runnable {// 实现Runnable接口，作为线程的实现类
		private String name; // 表示线程的名称

		// 构造函数
		public MyThread(String name) {
			this.name = name; // 通过构造方法配置name属性
		}

		@Override
		public void run() { // 覆写run()方法，作为线程 的操作主体
			while (true) {
				System.out.println(name + "运行，i = ");
			}
		}
	}

	public static void main(String args[]) {
		MyThread mt1 = new Runnable01().new MyThread("线程A--"); // 实例化对象
		MyThread mt2 = new Runnable01().new MyThread("线程B--"); // 实例化对象
		Thread t1 = new Thread(mt1); // 实例化Thread类对象
		Thread t2 = new Thread(mt2); // 实例化Thread类对象
		t1.start(); // 启动多线程
		t2.start(); // 启动多线程
	}

}
