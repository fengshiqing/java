/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.proxy.dynamic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggerHandler {

	private final IHelloWorld target;// 要代理的对象

	/**
	 * 构造函数
	 */
	public LoggerHandler(IHelloWorld target) {
		this.target = target;
	}

	public IHelloWorld getLoggerProxy() {
		// 当调用代理对象中的方法时，执行此代码
		InvocationHandler h = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
				// proxy.toString();// 造成内存溢出：java.lang.StackOverflowError

				String methodName = method.getName(); // 获取方法名称
				System.out.println(methodName + "【开始执行】");
				Object result = method.invoke(target, args);
				System.out.println(methodName + "【结束执行】【执行成功】【相应参数：" + result + "】");
				return result;
			}
		};

		return (IHelloWorld) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), h);
	}

}

class TestJdkDynamic {

	/**
	 * 功能描述：测试JDK动态代理
	 */
	@Test
	public void test() {
		// 动态代理方式调用
		IHelloWorld target = new HelloWorld();
		IHelloWorld proxy = new LoggerHandler(target).getLoggerProxy();
		int number = proxy.sayHello();
		System.out.println(number);
	}

}
