/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class ReadProperties implements CommandLineRunner {

    private final Environment env;

    @Override
    public void run(String... args) {
        System.err.println("【local.ip=】" + env.getProperty("local.ip"));
        System.err.println("【spring.application.name】" + env.getProperty("spring.application.name"));
        System.err.println("【spring.datasource.type】" + env.getProperty("spring.datasource.type"));
    }
}
