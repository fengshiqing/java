package com.kunning.javase.jvm;

public class TestClass {
	
	public static void main(String[] args) {
		//new A();
		
//		short s1 = 1;
//		short s2 = 2;
//		short s = (short) (s1 + s2);
//		System.out.println(s);
		
		
		// 调用class加载器
		//ClassLoader clApp11 = ClassLoader.getClassLoader();// 返回该类的加载器
		ClassLoader clApp = TestClass.class.getClassLoader();// 返回该类的加载器
		ClassLoader clApp1 = ClassLoader.getSystemClassLoader();// 返回该类的系统类加载器
		System.out.println(clApp);
		System.out.println(clApp1);
		
		// 调用上一层类加载器
		ClassLoader clExt = clApp.getParent();
		System.out.println(clExt);
		System.out.println(System.getProperty("java.ext.dirs"));
		
		// 调用根部类加载器
		ClassLoader clBoot = clExt.getParent();
		System.out.println(clBoot);
		System.out.println(System.getProperty("java.class.path"));// 返回该项目的classpath路径
		System.out.println(System.class.getClassLoader());
	}

	static class B {
		//int x;
		static {
			System.out.println("Load B");
		}
		public B() {
			System.out.println("Create B");
		}
		static {
			System.out.println("Load B");
		}
	}

	static class A extends B {
		static {
			System.out.println("Load A");
		}
		public A() {
			super();
			System.out.println("Create A");
			ClassLoader clApp = A.class.getClassLoader();
			System.out.println(clApp);
			System.out.println(clApp.getParent());

			//System.out.println(x);
		}
	}

}
