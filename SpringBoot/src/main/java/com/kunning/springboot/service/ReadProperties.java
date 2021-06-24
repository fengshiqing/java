/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * 功能描述：读取配置项
 *
 * @author fengshiqing
 * @since 2021-06-24
 */
@Service
@PropertySource(value={"classpath:conf/config.properties"})
public class ReadProperties implements CommandLineRunner {

    @Value("${name}")
    private String name;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("【读取配置文件】" + name);
    }
}
