package com.kunning.springbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：此starter 和 SpringBoot 对接，入口
 */
@Configuration
@ConditionalOnClass  // 条件注解，只有在 classpath 路径下 指定class文件时，才会实例化bean
@EnableConfigurationProperties(CustomProperties.class)
public class CustomAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean // 条件注解，仅当IOC容器中不存在指定类型的bean 时，才创建
    public CustomStarter hello() {
        return new CustomStarterImpl();
    }
}
