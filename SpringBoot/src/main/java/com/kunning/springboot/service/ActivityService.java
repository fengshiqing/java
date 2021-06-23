/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import com.kunning.springboot.aop.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 功能描述：活动 Service
 *
 * @author fengshiqing
 * @since 2020-08-23
 */
@Service
public class ActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

    /**
     * 功能描述：根据活动ID查询活动名称。
     * 先查缓存，再查数据库，数据库中查到再回写到缓存。
     *
     * @param id 活动ID
     *
     * @return 活动名称
     */
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
