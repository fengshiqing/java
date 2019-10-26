package com.kunning.commons.DesignPatterns;

/**
 * Singleton_在高并发环境下，会创建出多个对象。<br>
 * 
 * 此处进行加锁处理
 */

public class Singleton_3 {

	private static Singleton_3 single;// 1、私有静态变量

	private Singleton_3() {// 2、私有构造器
	}

	// Singleton_2方式有线程并发问题，需要进一步处理：
	public synchronized static Singleton_3 getInstance() {// 3、公有静态方法
		if (single == null) {
			single = new Singleton_3();
		}
		return single;
	}

}
