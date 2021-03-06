package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能描述：配置 测试。
 *
 * @author 冯仕清
 * @since 2019/12/10
 */
@Configuration
public class TestConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfiguration.class);

    public TestConfiguration() {
        LOGGER.info("【TestConfiguration容器启动初始化。。。】");
    }

    public static void main(String[] args) {
        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        // 如果加载spring-context.xml文件：
         ApplicationContext context1 = new ClassPathXmlApplicationContext("spring-context.xml");
    }

}
