/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller;

import com.kunning.springcloud.controller.response.RespData;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：产品controller。
 *
 * @author 冯仕清
 * @since 2022-07-20
 */
@AllArgsConstructor
@RestController
public class TestController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private final RedisTemplate<String, String> redisTemplate;


    /**
     * 功能描述：查询指定key的值。
     * 浏览器调用方式：http://localhost/redis/getValue?keyName=hello
     *
     * @param keyName keyName
     */
    @RequestMapping(value = "/redis/setValue")
    public String setValue(String keyName, String value) {
        redisTemplate.opsForValue().set(keyName, value);
        String a = redisTemplate.opsForValue().get(keyName);
        LOGGER.info("【设置redis的值：{}】", a);
        return a;
    }

    /**
     * 功能描述：查询指定key的值。
     * 浏览器调用方式：http://localhost/redis/getValue?keyName=hello
     *
     * @param keyName keyName
     */
    @RequestMapping(value = "/redis/getValue")
    public RespData<String> getValue(String keyName) {
        String str = redisTemplate.opsForValue().get(keyName);
        LOGGER.info("【读取redis的值：{}】", str);
        return new RespData<>(str);
    }

}
