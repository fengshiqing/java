/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kunning.springboot.Application;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest(classes = Application.class)
class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RedisService redisService;

    @Test
    void queryActivityName() {
        activityService.queryActivityName("feng12224524422424");

        System.out.println("【查看redis中的值：】" + redisService.get("feng12224524422424"));
    }
}