package com.kunning.springboot.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class TestConfiguration {

    public TestConfiguration() {
        System.out.println("【TestConfiguration容器启动初始化。。。】");
    }

    public static void main(String[] args) {

        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        // 如果加载spring-context.xml文件：
//         ApplicationContext context1 = new ClassPathXmlApplicationContext("spring-context.xml");
    }

}
