package com.kunning.javase.DesignPatterns.proxy.动态代理;

public class HelloWorld implements IHelloWorld {

	@Override
	public int sayHello() {
		System.out.println("HelloWorld");
		return 1 + 1;
	}

}
