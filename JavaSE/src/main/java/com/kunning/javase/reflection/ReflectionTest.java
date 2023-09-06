package com.kunning.javase.reflection;

import com.kunning.javase.reflection.annotation.DoSomething;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 功能描述：反射
 * 什么是反射：
 * 在程序的运行阶段，对于任意类，都能够知道这个类里面的属性和方法；
 * 对于任意对象，都可以调用它的任意方法和属性；
 * 这样一种通过动态获取信息和调用对象方法的功能就叫做反射。
 *
 * @author fengshiqing
 * @since 2019/05/09
 */
public class ReflectionTest {

	// 关于类的加载器：ClassLoader
	@Test
	public void test_5() throws Exception {
		ClassLoader loader1 = ClassLoader.getSystemClassLoader();
		System.out.println(loader1);

		ClassLoader loader2 = loader1.getParent();
		System.out.println(loader2);

		ClassLoader loader3 = loader2.getParent();
		System.out.println(loader3);

		Class clazz1 = Person.class;
		ClassLoader loader4 = clazz1.getClassLoader();
		System.out.println(loader4);

		String className = "java.lang.String";
		Class clazz2 = Class.forName(className);
		ClassLoader loader5 = clazz2.getClassLoader();
		System.out.println(loader5);

		// 掌握如下
		// 法一：
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream("reflection\\jdbc.properties");
		// 法二：
		//FileInputStream is = new FileInputStream(new File("jdbc1.properties"));

		Properties pros = new Properties();
		pros.load(is);
		String name = pros.getProperty("user");
		System.out.println(name);

		String password = pros.getProperty("password");
		System.out.println(password);
	}

	/**
	 * 功能描述：获取Class类的实例（字节码）
	 * 4种方式。
	 */
	@Test
	public void Instance() throws ClassNotFoundException {
		// 1.调用运行时类本身的.class属性
		Class<Person> clazz1 = Person.class;
		System.out.println(clazz1.getName());

		Class<String> clazz2 = String.class;
		System.out.println(clazz2.getName());

		// 2.通过运行时类的对象获取
		Person p = new Person();
		Class<? extends Person> clazz3 = p.getClass();
		System.out.println(clazz3.getName());

		// 3.通过Class的静态方法获取.通过此方式，体会一下，反射的动态性。
		String className = "com.kunning.javase.reflection.Person";
		Class<?> clazz4 = Class.forName(className);
//			clazz4.newInstance();
		System.out.println(clazz4.getName());

		// 4.（了解）通过类的加载器
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class<?> clazz5 = classLoader.loadClass(className);
		System.out.println(clazz5.getName());

		System.out.println(clazz1 == clazz3);// true
		System.out.println(clazz1 == clazz4);// true
		System.out.println(clazz1 == clazz5);// true
	}

	/*
	 * java.lang.Class:是反射的源头。
	 * 我们创建了一个类，通过编译（javac.exe）,生成对应的.class文件。之后我们使用java.exe加载（JVM的类加载器完成的）
	 * 此.class文件，此.class文件加载到内存以后，就是一个运行时类，存在在缓存区。那么这个运行时类本身就是一个Class的实例！
	 * 1.每一个运行时类只加载一次！
	 * 2.有了Class的实例以后，我们才可以进行如下的操作： 
	 * 		1）*创建对应的运行时类的对象
	 * 		2）获取对应的运行时类的完整结构（属性、方法、构造器、内部类、父类、所在的包、异常、注解、...），代码块就获取不到了。
	 * 		3）*调用对应的运行时类的指定的结构(属性、方法、构造器)
	 * 		4）反射的应用：动态代理
	 */
	@Test
	public void test_3() {
		Person p = new Person();
		Class clazz = p.getClass();// 通过运行时类的对象，调用其getClass()，返回其运行时类。
		System.out.println(clazz);
	}

	// 有了反射，可以通过反射创建一个类的对象，并调用其中的结构
	@Test
	public void test_2() throws Exception {

		Class<Person> clazz = Person.class;
//		Class clazz1 = String.class;

		// 1.创建clazz对应的运行时类Person类的对象
		Person p = (Person) clazz.newInstance();
		System.out.println(p);
		// 2.通过反射调用运行时类的指定的属性
		// 2.1
		Field f1 = clazz.getField("name");
		f1.set(p, "LiuDeHua");
		System.out.println(p);
		// 2.2
		Field f2 = clazz.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 20);
		System.out.println(p);

		// 3.通过反射调用运行时类的指定的方法
		Method m1 = clazz.getMethod("show");
		m1.invoke(p);

		Method m2 = clazz.getMethod("display", String.class);
		m2.invoke(p, "CHN");

	}

	// 在有反射以前，如何创建一个类的对象，并调用其中的方法、属性
	@Test
	public void test_1() {
		Person p = new Person();
//			Person p1 = new Person();
		p.setAge(10);
		p.setName("TangWei");
		System.out.println(p);
		p.show();
//			p.display("HK");
	}

	@Test
	public void annotation() throws NoSuchMethodException {

		Class<Person> clazz = Person.class;
		Class<DoSomething> anno = DoSomething.class;

		// 获取 类 上的注解
		if(clazz.isAnnotationPresent(anno)) {
			DoSomething annotation = clazz.getAnnotation(anno);
			System.out.println(annotation.key());
			System.out.println(annotation.cacheName());
			System.out.println(annotation.needLog());
		}

		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			// 获取 方法 上的注解
			if (method.isAnnotationPresent(anno)) {
				DoSomething annotation = method.getAnnotation(anno);
				System.out.println(annotation.key());
				System.out.println(annotation.cacheName());
				System.out.println(annotation.needLog());
			}
		}
	}

	/**
	 * 功能描述：通过反射获取 构造函数
	 */
	@Test
	public void getConstructor() throws NoSuchMethodException {

		// 这个方法只能获取 public 权限的构造方法
		// 获得 public 空参构造器，没有显式声明构造函数，也能够获取默认的空参构造函数
		Constructor<Person> constructor1 = Person.class.getConstructor();
		Constructor<Person> constructor2 = Person.class.getConstructor(String.class); // 根据指定参数获得 public 构造器
		Constructor<Person> constructor3 = Person.class.getConstructor(String.class, int.class); // 根据指定参数获得 public 构造器
		Constructor<?>[] constructor4 = Person.class.getConstructors();
		Constructor<Person>[] constructor5 = (Constructor<Person>[]) Person.class.getConstructors();

		// 要想获得私有的构造函数，必须用 getDeclaredConstructor，这个既能获得 public 的，也能给你获取 private 的。
		Constructor<Person> constructor6 = Person.class.getDeclaredConstructor();
		Constructor<?>[] constructor7 = Person.class.getDeclaredConstructors();
		Constructor<Person>[] constructor8 = (Constructor<Person>[]) Person.class.getDeclaredConstructors();
	}

	/**
	 * 功能描述：通过反射获取 一般方法
	 */
	@Test
	public void getMethod() throws NoSuchMethodException {

		// 这个方法只能获取 public 权限的一般方法（获取不到构造方法）
		// 获得类中的一般方法
		Method method1 = Person.class.getMethod("getName");
		Method method2 = Person.class.getMethod("setName", String.class);
		Method[] method3 = Person.class.getMethods();

		// 要想获得私有的方法，必须用 getDeclaredMethod，这个既能获得 public 的，也能给你获取 private 的。
		Method method4 = Person.class.getDeclaredMethod("sayHello");
		Method[] methods5 = Person.class.getDeclaredMethods();
	}

	/**
	 * 功能描述：通过反射获取 属性
	 */
	@Test
	public void getField() throws NoSuchFieldException {
		Class<Person> clazz = Person.class;

		// 这个方法只能获取 public 权限的一般方法（获取不到构造方法）
		// 获得类中的一般方法
		Field field1 = clazz.getField("name");
		Field[] Field2 = clazz.getFields();

		// 要想获得私有的属性方法，必须用 getDeclaredMethod，这个既能获得 public 的，也能给你获取 private 的。
		Field Field3 = clazz.getDeclaredField("gender");
		Field[] Field4 = clazz.getDeclaredFields();
	}

}
