/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 功能描述：随着Springboot的启动而执行，并设置执行的顺序。
 *
 * @author 冯仕清
 * @since 2021-06-24
 */
@Component
public class Startup_1 implements CommandLineRunner, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(Startup_1.class);

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("【执行顺序 01】");
        System.out.println("【执行顺序 01】");
    }
}
