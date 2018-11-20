package pattern;

/**
 * Singleton_3的方式还是有问题，每一次调用都需要同步，实际上只有第一次创建对象时才需要同步，以后每次获取对象同步都是一种累赘，严重降低效率，必须进一步优化：<br>
 * 
 * @see https://www.cnblogs.com/zhaoyan001/p/6365064.html
 */
public class Singleton_4 {

	private volatile static Singleton_4 single;// 1、私有静态变量

	private Singleton_4() {// 2、私有构造器
	}

	public static Singleton_4 getInstance() {// 3、公有静态方法
		if (single == null) {
			synchronized (Singleton_4.class) {// 双重检查加锁，Class clazz = Singleton.class;这是Class类的对象
				if (single == null) {
					single = new Singleton_4();
				}
			}
		}
		return single;
	}
	
}

//到这里，延时加载单例模式才算完美，能大大减少getInstance()的时间耗费，提高性能。
//注意：双重检查加锁不适用于1.4及更早版本的jdk
