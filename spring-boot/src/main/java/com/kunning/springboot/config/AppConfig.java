package com.kunning.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：学习 @Configuration、@Bean、@Import 注解
 *
 * @author 冯仕清
 * @since 2101/12/25
 */
// https://www.w3cschool.cn/wkspring/tlbk1icp.html
@Slf4j
@Configuration // 取代 application.xml配置文件
public class AppConfig {

    /**
     * 构造函数
     */
    public AppConfig() {
        log.info("【初始化 AppConfig 配置】");
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Foo foo() {
        log.info("【创建 Foo 对象】");
        return new Foo();
    }

    static class Foo {
        public void init() {
            log.info("【init 对象 Foo】");
        }

        public void destroy() {
            log.info("【destroy 对象 Foo】");
        }
    }

}
