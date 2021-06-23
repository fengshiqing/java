/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.kunning.springbootstarter.CustomStarter;

/**
 * 功能描述：运行自定义的starter
 *
 * @author kunning
 * @since 2021-06-23
 */
@Service
@Order(10)
public class CustomStarterService implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomStarterService.class);

    private final CustomStarter customStarter;

    public CustomStarterService(CustomStarter customStarter) {
        this.customStarter = customStarter;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("【执行自定义starter的程序】");
        System.out.println("【执行自定义starter的程序】");
        customStarter.welcome();
    }
}
