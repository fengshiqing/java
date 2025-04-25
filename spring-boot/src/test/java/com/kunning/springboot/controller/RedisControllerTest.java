/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.kunning.springboot.MyApplication;
import com.kunning.springboot.service.RedisService;

@SpringJUnitConfig
@SpringBootTest(classes = MyApplication.class) // 加载springboot的测试环境配置
@Rollback(value = false) // springboot单元测试 默认会回滚事务，置为false强制不回滚。
@AutoConfigureMockMvc // 注入MockMvc
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
    }

}