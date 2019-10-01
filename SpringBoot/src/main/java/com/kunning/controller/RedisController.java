package com.kunning.controller;

import com.kunning.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * 功能描述：redis控制层
 *
 * @author 冯仕清
 * @since 2019年10月1日 19点16分
 */
@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/redis/setValue")
    public void demoTest() {
        redisService.set("1", "value22222");
        System.out.println("【值为：】" + redisService.get("1"));
    }
}
