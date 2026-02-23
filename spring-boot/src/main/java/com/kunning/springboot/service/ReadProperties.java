/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 功能描述：读取配置项。
 * <p>
 * CommandLineRunner 和 ApplicationRunner 的区别看这里：<a href="https://blog.csdn.net/m0_37739193/article/details/85255317">...</a>
 *
 * @author 冯仕清
 * @since 2021-06-24
 */
@Slf4j
@Order(10)
@Service
@PropertySource(value = {"classpath:config.properties"})
public class ReadProperties implements CommandLineRunner, ApplicationRunner {

    @Value("${name}")
    private String name;


    @Override
    @NullMarked
    public void run(String... args) throws Exception {
        log.info("【读取 config.properties 文件中的配置，name：{}】", name);
    }

    @Override
    @NullMarked
    public void run(ApplicationArguments args) throws Exception {

    }
}
