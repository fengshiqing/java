package com.kunning.javaWeb.springIoC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kunning.javaWeb.beans.HelloWorld;

/**
 * 获取IoC容器中的对象
 * 
 * @author 冯仕清
 */
public class SpringIoC {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
	}

}
