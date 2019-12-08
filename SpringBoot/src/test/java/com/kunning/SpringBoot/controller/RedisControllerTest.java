package com.kunning.springboot.controller;

import com.kunning.springboot.service.RedisService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisControllerTest {

    @Autowired
    private RedisService redisService;

    @Test
    void demoTest() {
        redisService.set("1", "value22222");
        System.out.println("【值为：】" + redisService.get("1"));
    }
}