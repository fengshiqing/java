package com.kunning.springboot.controller;

import com.kunning.springboot.Application;
import com.kunning.springboot.service.RedisService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class) //测试运行在spring环境下
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