/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.common.javase.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 功能描述：读取项目中的配置文件
 * 看这个：http://www.cnblogs.com/coder-wzr/p/8418236.html
 * http://www.cnblogs.com/yejg1212/p/3270152.html
 */
public class ReadPoperties {

	/**
	 * 读取项目中的配置文件
	 */
	public static void main(String[] args) throws IOException {
		// 1、读取项目中的配置文件
		InputStream input = ReadPoperties.class.getClassLoader().getResourceAsStream("2.properties");
		// InputStream input = ReadProperties.class.getClassLoader().getResourceAsStream("fengshiqing2.properties");配置文件的路径不能直接方法哦工程的路径下
		Properties prop_1 = new Properties();
		prop_1.load(input);// 如果配置了中文，会产生乱码
		// prop.load(new InputStreamReader(input, "UTF-8"));// 这种方式可以读取中文，不会乱码
		System.out.println(prop_1.size());
		System.out.println(prop_1.getProperty("name"));
		System.out.println(prop_1.getProperty("gender"));
		System.out.println(prop_1.getProperty("age"));
		System.out.println(prop_1.getProperty("asdf"));

		System.out.println();

		// Class.getResource(String path)
		// 配置文件放的位置不同，读取时的地址也是不同的。要明白原理！！！
		System.out.println(ReadPoperties.class.getResource(""));// path不以’/'开头时，默认是从此类所在的包下取资源
		System.out.println(ReadPoperties.class.getResource("/"));// path  以’/'开头时，则是从ClassPath根下获取，在这里就是相当于bin目录(Eclipse环境下)
		// 获取1234四个配置文件
		System.out.println(ReadPoperties.class.getResource("3.properties"));//  path不以’/'开头时，默认是从此类所在的包下取资源
		System.out.println(ReadPoperties.class.getResource("/io/3.properties"));//  这种写法等价于上面的
		System.out.println(ReadPoperties.class.getResource("/2.properties"));
		// System.out.println(ReadPoperties.class.getResource("/././1.properties"));// 直接放在项目下的“1.properties”是获取不到的，用下面的方式能获取到
		Properties prop_2 = new Properties();
		prop_2.load(new FileReader("1.properties"));// 这种路径的加载方式是"1.properties"直接放在项目根路径下
		System.out.println("项目路径下的配置文件：" + prop_2.getProperty("name"));
		System.out.println("项目路径下的配置文件：" + prop_2.getProperty("gender"));
		System.out.println("项目路径下的配置文件：" + prop_2.getProperty("age"));
		System.out.println("项目路径下的配置文件：" + prop_2.getProperty("asdf"));
		
		// Class.getResource和Class.getResourceAsStream在使用时，路径选择上是一样的。
		
		System.out.println("----------------------------------------------------------------------------------------");
		
		// Class.getClassLoader().getResource(String path)
		// path不能以’/'开头；path只能从ClassPath根下获取（就是bin目录下）；
		String url_2 = ReadPoperties.class.getClassLoader().getResource("2.properties").getPath();
		// String url_2 = ReadPoperties.class.getClassLoader().getResource("io/3.properties").getPath();
		System.out.println("url_2：" + url_2);
		System.out.println(ReadPoperties.class.getClassLoader().getResource(""));
		Properties prop222 = new Properties();
		prop222.load(new FileReader(url_2));
		System.out.println(prop222.size());
		System.out.println(prop222.getProperty("name"));
		System.out.println(prop222.getProperty("gender"));
		System.out.println(prop222.getProperty("age"));
		System.out.println(prop222.getProperty("asdf"));
		
		// Class.getClassLoader().getResource和Class.getClassLoader().getResourceAsStream在使用时，路径选择上也是一样的。
		// InputStream input = ReadPoperties.class.getClassLoader().getResourceAsStream("2.properties");
		
		
		// 从结果来看【Class.getResource("/") == Class.getClassLoader().getResource("")】
	}

}
