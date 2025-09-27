/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.controller;

import com.fengshiqing.springcloud.service.client.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述：redis控制层
 *
 * @author 冯仕清
 * @since 2019年10月1日 19点16分
 */
@Slf4j
@AllArgsConstructor
@Controller
@ResponseBody
public class RedisController {

    private final RedisService redisService;


    /**
     * 功能描述：查询指定key的值。
     *
     * @param keyName keyName
     * @param value value
     */
    @RequestMapping(value = "/redis/setValue")
    public void setValue(@RequestParam("keyName") String keyName, @RequestParam("value") String value) {
        log.info("【setValue】【keyName:{}, value:{}】", keyName, value);
        redisService.set(keyName, value);
        log.info("【插入redis的值为：{}】", redisService.get(keyName));
    }

    /**
     * 功能描述：查询指定key的值。
     *
     * @param keyName keyName
     */
    @RequestMapping(value = "/redis/getValue")
    public void getValue(String keyName) {
        log.info("【读取redis的值：{}】", redisService.get(keyName));
    }
}
