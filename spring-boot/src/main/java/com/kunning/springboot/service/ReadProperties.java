/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * 功能描述：读取配置项。
 *
 * CommandLineRunner 和 ApplicationRunner 的区别看这里：https://blog.csdn.net/m0_37739193/article/details/85255317
 *
 * @author fengshiqing
 * @since 2021-06-24
 */
@Service
@PropertySource(value = {"classpath:config.properties"})
public class ReadProperties implements CommandLineRunner, Ordered, ApplicationRunner {

    @Value("${name}")
    private String name;

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("【读取配置文件】" + name);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
