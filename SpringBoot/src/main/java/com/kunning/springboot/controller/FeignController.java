/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.controller;

import com.fengshiqing.common.bean.User;
import com.kunning.springboot.feign.Microservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：对外提供接口。
 *
 * @author fengshiqing
 * @since 2022-04-23
 */
@RestController
public class FeignController {
    public static final Logger LOGGER = LoggerFactory.getLogger(Microservice.class);
    @Autowired
    private Microservice microservice;

    @RequestMapping(path = "query")
    public String query() {
        LOGGER.info("【查询用户信息】【start】");
        return microservice.post();
    }


    @RequestMapping(path = "queryUserInfo")
    public User queryUserInfo(long id) {
        LOGGER.info("【查询用户信息】【start】【id:{}】", id);
        return microservice.queryUserInfo(id);
    }
}
