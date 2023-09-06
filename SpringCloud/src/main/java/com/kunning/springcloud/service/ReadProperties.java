/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ReadProperties implements CommandLineRunner {

    private final Environment env;

    public ReadProperties(Environment env) {
        this.env = env;
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("【local.ip=】" + env.getProperty("local.ip"));
        System.err.println("spring.application.name -----" + env.getProperty("spring.application.name"));
        System.err.println("【spring.datasource.type】" + env.getProperty("spring.datasource.type"));
    }
}
