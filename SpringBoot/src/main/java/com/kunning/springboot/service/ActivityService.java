/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import com.kunning.springboot.aop.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

    @RedisCache(key="#id")
    public String queryActivityName(String id) {
        LOGGER.info("【queryActivityName】【start】【id:{}】", id);

        // 查询数据库，获取活动名称。
        String activityName = "数据库中的活动名称";

        LOGGER.info("【queryActivityName】【end】【activityName:{}】", activityName);
        System.out.println("【queryActivityName】【end】【activityName:{}】" + activityName);
        return activityName;
    }

}
