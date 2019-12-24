package com.kunning.web.springIoC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kunning.web.springIoC.beans.HelloWorld;

/**
 * 功能描述：获取IoC容器中的对象
 * 
 * @author 冯仕清
 * @since 2019/12/24
 */
public class SpringIoC {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/Beans.xml"); // 加载 Spring 容器的配置文件
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
		HelloWorld obj1 = context.getBean(HelloWorld.class);
		obj1.getMessage();
	}

}
