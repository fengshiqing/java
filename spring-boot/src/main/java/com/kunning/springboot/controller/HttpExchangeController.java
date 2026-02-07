/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.controller;

import com.kunning.springboot.service.client.ProductClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：对外提供接口。
 *
 * @author 冯仕清
 * @since 2022-04-23
 */
@AllArgsConstructor
@Slf4j
@RestController
public class HttpExchangeController {

    private final ProductClient productClient;

    // =================================================================================================================

    @RequestMapping(path = "query")
    public Map<String, String> queryById() {
        log.info("【查询用户信息】【start】");
        return productClient.queryById(123L);
    }


    @RequestMapping(path = "queryUserInfo")
    public List<Map<String, String>> queryUserInfo(long id) {
        log.info("【查询用户信息】【start】【id:{}】", id);
        return productClient.search("手机", 1000L);
    }
}
