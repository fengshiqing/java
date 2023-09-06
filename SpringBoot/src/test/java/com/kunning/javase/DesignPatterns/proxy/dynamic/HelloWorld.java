/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.proxy.dynamic;

public class HelloWorld implements IHelloWorld {

	@Override
	public int sayHello() {
		System.out.println("嗨，JDK动态代理，你好呀...");
		return 1 + 1;
	}

}
