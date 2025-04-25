/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fengshiqing.common.bean.User;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述：feign接口。
 *
 * @author fengshiqing
 * @since 2022-04-19
 */
@Service
@FeignClient(name = "CloudApplication")
public interface Microservice {

    @PostMapping("/provider")
    // void post(@RequestBody User user);
    String post();

    @RequestMapping("/queryUserInfo")
    User queryUserInfo(@RequestParam("id") long id);
}
