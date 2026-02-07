/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 功能描述：随着Springboot的启动而执行，并设置执行的顺序。
 * 设置命令行参数：--spring.profile.active=test，但ApplicatonRunner接口的方法参数ApplicationArguments（是个对象）比CommandLineRunner接口的方法参数（是个可以接收多个string的参数）功能更强大。
 * ApplicatonRunner接口的方法参数ApplicationArguments既可以获取参数的字符串，也可以直接获取key；
 * CommandLineRunner接口的方法参数只能获取参数的字符串。
 * ApplicationRunner接口的实现方法比CommandLineRunner接口的实现方法前执行（当然也可以设置@Order的值来决定谁先执行）
 *
 * @author 冯仕清
 * @since 2021-06-24
 */
@Component
@Order(3)
public class Startup_3 implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Startup_3.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LOGGER.info("【执行顺序 03】");
        LOGGER.info("启动预加载数据(ApplicationRunner):{}", args);
        System.out.println("【执行顺序 03】");
    }
}
