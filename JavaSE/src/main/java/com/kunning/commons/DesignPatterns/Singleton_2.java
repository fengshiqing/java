package com.kunning.commons.DesignPatterns;

/**
 * 对性能有点要求的，可以将 Singleton_1 改进成“延时加载”（懒汉式）。<br>
 * 
 * 这种在高并发环境下，会创建出多个对象，改进型见 {@link Singleton_3}
 */
public class Singleton_2 {

	private static Singleton_2 single;// 1、私有静态变量

	private Singleton_2() {// 2、私有构造器
	}

	public static Singleton_2 getInstance() {// 3、公有静态方法
		// 这种方式有线程并发问题，需要进一步处理
		if (single == null) {
			single = new Singleton_2();
		}
		return single;
	}

}
