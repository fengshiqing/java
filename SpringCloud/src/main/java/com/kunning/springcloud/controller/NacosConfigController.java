/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：nacos配置
 *
 * @author 冯仕清
 * @since 2022-08-07
 */
@RefreshScope
@RestController
public class NacosConfigController {

    @Value(value="${user.id:默认值}")
    private String id;
    @Value(value="${user.name:默认值}")
    private String name;
    @Value(value="${user.age:默认值}")
    private String age;

    @GetMapping("/provider/getNacosConfig")
    public String providerTest() {
        return "从 nacos配置中心 获取数据：id=" + id + ", name=" + name + ", age=" + age;
    }
}
