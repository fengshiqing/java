/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：配置Mybatis
 *
 * @author 冯仕清
 * @since 2020/03/20 19:09
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "com.fengshiqing.springcloud.**.mapper") // 和 @ComponentScan 注解功能类似
public class MyBatisConfig {

    /**
     * 当在构造方法上使用 @Autowired注解 的时候，需要注意的一点就是一个类中只允许有一个构造方法使用此注解。
     * 此外，在Spring4.3后，如果一个类仅仅只有一个构造方法，那么即使不使用此注解，那么Spring也会自动注入相关的bean。
     */
    public MyBatisConfig() {
        log.info("【初始化 Mybatis 配置】");
    }

}
