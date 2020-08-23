/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import com.kunning.springboot.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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