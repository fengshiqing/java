/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller;

import com.fengshiqing.common.bean.User;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 *
 * @author kunning
 * @since 2022-04-23
 */
@Slf4j
@RestController
public class MicroServiceController {


    /**
     * 功能描述：微服务提供者
     */
    @RequestMapping("/provider")
    public String provider() {
        log.info("【进入微服务生产者】【start】");
        return "成功调用到了微服务";
    }

    /**
     * 功能描述：微服务提供者
     */
    @RequestMapping("/queryUserInfo")
    public User queryUserInfo(@NotNull long id) {
        log.info("【进入微服务生产者】【start】【id:{}】", id);
        return new User().setUsername("冯仕清").setPassword("fengshiqing");
    }

}
