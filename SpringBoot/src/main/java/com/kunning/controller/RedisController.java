package com.kunning.controller;

import com.kunning.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;


    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.124.11");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }

    @RequestMapping(value = "/test")
    public void demoTest() {
        redisService.set("1", "value22222");
        System.out.println("【值为：】" + redisService.get("1"));
    }
}
