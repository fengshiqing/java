/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.kunning.springboot.MyApplication;

@SpringBootTest(classes = MyApplication.class) // 是spring-test提供的测试执行单元类(是Spring单元测试中SpringJUnit4ClassRunner的新名字)
public class RedisUtilTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void aaa() {
        redisTemplate.opsForValue().set("hello11", "111");
        System.out.println("【hello】" + redisTemplate.opsForValue().get("hello"));
    }

}