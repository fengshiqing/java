package com.kunning.commons.DesignPatterns;

/**
 * 单态设计模式：就是要保证在整个程序中某个类只能存在一个对象，这个类不能再创建第二个对象。<br>
 * 单例模式的三要素：<br>
 * 1、私有静态变量，引用此类创建的对象，此成员变量需要在不创建对象的情况下就能被外部使用，所以用static修饰，<br>
 * 只用static修饰，外部得到这个变量时仍可以修改其值，所以需要私有private，以此确保外部其他类不能修改。<br>
 * 由于需要返回一个对象，那么我们就需要在类内部自己创建一个对象，并使用成员变量记住它。<br>
 * 由于该类只能创建一个对象，所以要静态，只在加载时创建。我们不希望其他类修改这个成员变量，所以将其私有。<br>
 * 2、私有构造函数，阻止外部创建对象。<br>
 * 3、公有静态方法，为了能让其他类使用这个成员变量，需要一个get方法获取单例对象，此方法需要在不创建对象的情况下使用，所以要静态。<br>
 */
public class Singleton_1 {

	// 加载此类时就创建了对象，“急切”（饿汉式）。这种对性能有一定的影响，进阶版的见 {@link Singleton_2}
	private static Singleton_1 single = new Singleton_1();// 1、私有静态变量

	private Singleton_1() {// 2、私有构造器
	}

	public static Singleton_1 getInstance() {// 3、公有静态方法
		return single;
	}
}
