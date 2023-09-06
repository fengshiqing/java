/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.proxy;

/*
 一：代理模式（静态代理）

          代理模式是常用设计模式的一种，我们在软件设计时常用的代理一般是指静态代理，也就是在代码中显式指定的代理。
          静态代理由 业务实现类、业务代理类 两部分组成。业务实现类 负责实现主要的业务方法，业务代理类负责对调用的业务方法作拦截、过滤、预处理，
          主要是在方法中首先进行预处理动作，然后调用业务实现类的方法，还可以规定调用后的操作。
          我们在需要调用业务时，不是直接通过业务实现类来调用的，而是通过业务代理类的同名方法来调用被代理类处理过的业务方法。
          静态代理的实现：
 */

/**
 * 代理模式。<br>
 * 这种是静态代理，就是接口和实现是一一对应的，换个被代理的类，就要新建一个对应的接口。<br>
 * 后面学了反射就可以用动态代理。<br>
 *
 * SLF4J和Log框架的绑定就是通过静态代理实现的。<br>
 *
 * 静态代理思想：<br>
 * 1、一个公共接口，<br>
 * 2、两个实现类，其中一个代理类，另一个是被代理类，<br>
 * 3、代理类中创建被代理类的引用，并调用被代理类的方法。
 *
 * 这个讲的不错：https://blog.csdn.net/MY9_19/article/details/117197190
 */
public class ProxyStaticTest {

	public static void main(String[] args) {
		IAction iAction = new ProxyObject();
		iAction.action();
	}

	// 一个公共接口
	interface IAction {
		void action();
	}

	// 代理类
	static class ProxyObject implements IAction {
		IAction iActionObj;// 代理类中创建被代理类的引用，可以在声明变量时初始化，也可以新建个构造函数用于初始化。

		public ProxyObject() {// 之所以称为静态（编译期的叫静态），因为在此处必须和“被代理类”绑定在一起，强耦合。
			System.out.println("代理类创建成功");
			iActionObj = new ActionImpl();// 代理类中创建被代理类的引用，也可以在调用时new出此对象
		}

		// 一般都使用上边的构造函数，下边的这个还需要调用创建对象，不太好。
		public ProxyObject(ActionImpl actionImpl) {
			System.out.println("代理类创建成功");
			iActionObj = actionImpl;// 代理类中创建被代理类的引用
		}

		@Override
		public void action() {
			System.out.println("代理类开始执行");
			iActionObj.action();// 调用被代理类的方法
			System.out.println("代理类结束执行");
		}
	}

	// 被代理类
	static class ActionImpl implements IAction {
		@Override
		public void action() {
			System.out.println("被代理类开始执行");
			System.out.println("被代理类的具体逻辑。。。");
			System.out.println("被代理类结束执行");
		}
	}

}


/*
 * 静态代理的缺点很明显：
 * 一个代理类只能对一个业务接口的实现类进行包装，如果有多个业务接口的话就要定义很多实现类和代理类才行。
 * 而且，如果代理类对业务方法的预处理、调用后操作都是一样的（比如：调用前输出提示、调用后自动关闭连接），则多个代理类就会有很多重复代码
 * 。这时我们可以定义这样一个代理类，它能代理所有实现类的方法调用：根据传进来的业务实现类和方法名进行具体调用。——那就是动态代理。
 *
 */