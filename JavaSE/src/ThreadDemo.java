
/**
 * <获取线程名>
 */
public class ThreadDemo {

	class MyThread implements Runnable { // 实现Runnable接口
		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName() + "运行中，i=" + i); // 取得当前线程的名称
			}
		}
	};

	public static void main(String args[]) {
		MyThread my = new ThreadDemo().new MyThread(); // 定义Runnable子类对象
		new Thread(my).start(); // 系统自动设置线程名称
		new Thread(my, "线程A").start(); // 手工设置线程名称
	}

}
