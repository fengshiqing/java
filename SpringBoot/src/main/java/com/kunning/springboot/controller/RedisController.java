package com.kunning.springboot.controller;

import com.kunning.springboot.service.RedisService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述：redis控制层
 *
 * @author 冯仕清
 * @since 2019年10月1日 19点16分
 */
@Controller
@ResponseBody
@Api(tags = "redis接口")
public class RedisController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/redis/setValue")
    public void demoTest() {
        redisService.set("1", "value22222");
        LOGGER.info("【插入redis的值为：】" + redisService.get("1"));
    }
}
