package com.kunning.JavaSE.DesignPatterns.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggerHandler {

	private IHelloWorld target;// 要代理的对象

	/**
	 * 构造函数
	 */
	public LoggerHandler(IHelloWorld target) {
		this.target = target;
	}

	@SuppressWarnings("rawtypes")
	public IHelloWorld getLoggerProxy() {
		// 当调用代理对象中的方法时，执行此代码
		InvocationHandler h = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// proxy.toString();// 造成内存溢出：java.lang.StackOverflowError

				String methodName = method.getName();
				System.out.println(methodName + "开始执行");
				Object result = method.invoke(target, args);
				System.out.println(methodName + "【结束执行】【执行成功】【相应参数：" + result + "】");
				return result;
			}
		};

		IHelloWorld proxy = (IHelloWorld) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), h);
		return proxy;
	}

}
