/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.common.javase.jvm;

/**
 * JVM 中静态变量加载初始化的顺序可以归纳总结如下：<br>
 * 父类static块 -> 子类static块 -> 父类{}块 -> 父类构造函数 -> 子类{}块 -> 子类构造函数。<br>
 * // static 修饰的变量和 static 块具有一样的优先级，故两者具有前后顺序。
 * 
 * @see https://www.xttblog.com/?p=3397
 */
public class Initialize {

	private static Initialize tester = new Initialize();

	private static int count1;

	private static int count2 = 2;

	public Initialize() {
		count1++;
		count2++;
		System.out.println("" + count1);
		System.out.println("" + count2);
	}

	public static Initialize getTester() {
		return tester;
	}

	// 业余草：www.xttblog.com
	public static void main(String[] args) {
		Initialize.getTester();
		System.out.println("" + count1);
		System.out.println("" + count2);
	}

}

/*
 * 先加载，为声明的变量分配内存空间（此时不会进行初始化，只会赋予默认值） JAVA
 * 类首次装入时，开始加载静态变量和静态块，也就是说会首先为静态区域分配内存空间，此时 tester、count1、count2 都已经分配空间，其中
 * tester 为一个引用空间，count1、count2 为默认值 0。
 * 
 * 第二步开始执行 private static Test tester = new Test() 这段代码，调用构造器打印出 count1、count2
 * 分别为 1 和 1 。
 * 
 * 然后依次执行一下代码 private static int count1; private static int count2 = 2;
 * 此时，count2 被重置为 2，因此如果此时再次打印的话 count1、count2 的值应该为 1 和 2。
 */
