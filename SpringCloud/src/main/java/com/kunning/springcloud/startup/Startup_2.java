/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springcloud.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 功能描述：随着Springboot的启动而执行，并设置执行的顺序。
 *
 * @author fengshiqing
 * @since 2021-06-24
 */
@Component
@Order(2)
public class Startup_2 implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Startup_2.class);

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("【执行顺序 02】");
        LOGGER.info("启动预加载数据(CommandLineRunner):{}", (Object) args);
        System.out.println("【执行顺序 02】");
    }
}
