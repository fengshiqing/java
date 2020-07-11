package com.kunning.javase.jvm;

/**
 * 类加载器：<br>
 * 当JVM（Java虚拟机）启动时，会形成由三个类加载器组成的初始类加载器层次结构：系统类加载器的父类是扩展类加载器，扩展类加载器的父类是引导类加载器。<br>
 * BootStrap ClassLoader——负责加载系统类（JAVA_HOME/jre/lib/rt.jar）<br>
 * ----Extension ClassLoader——负责加载扩展类（JAVA_HOME/jre/lib/ext/*.jar），类名：ExtClassLoader.java<br>
 * --------System ClassLoader——附加加载应用类（classpath指定目录中的类和jar包中的类），类名：AppClassLoader.java<br>
 */
public class ClassLoaderTest {

	public static void main(String[] args) {
		ClassLoaderTest test = new ClassLoaderTest();
		ClassLoader loader_1 = test.getClass().getClassLoader();
		System.out.println(loader_1);// sun.misc.Launcher$AppClassLoader@2a139a55
		System.out.println(loader_1.getParent());// sun.misc.Launcher$ExtClassLoader@7852e922
		System.out.println(loader_1.getParent().getParent());// null，顶级父类是C语言写的，无法获取到。

		System.out.println(String.class.getClassLoader());// null，说明String类是由顶级类加载器加载的
		System.out.println(ClassLoader.getSystemClassLoader());// sun.misc.Launcher$AppClassLoader@2a139a55
	}

}
