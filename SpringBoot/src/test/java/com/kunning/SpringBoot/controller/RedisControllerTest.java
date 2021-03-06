/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.kunning.springboot.Application;
import com.kunning.springboot.service.RedisService;

@SpringJUnitConfig
@SpringBootTest(classes = Application.class) //加载springboot的测试环境配置
@Rollback(value = false) //springboot单元测试 默认会回滚事务，置为false强制不回滚。
@AutoConfigureMockMvc //注入MockMvc
class RedisControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RedisService redisService;

    @Test
    void demoTest() {
        redisService.set("feng", "冯仕清");
        System.out.println("【值为：】" + redisService.get("feng"));
    }
}