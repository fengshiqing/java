/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：nacos配置中，配置中心上的数据变化了后，这里就能查询到最新的数据。
 *
 * @author 冯仕清
 * @since 2022-08-07
 */
@RefreshScope
@RestController
public class NacosConfigController {

    @Value(value = "${biz.user.id:默认值}")
    private String id;

    @Value(value = "${biz.user.name:默认值}")
    private String name;

    @Value(value = "${biz.user.age:默认值}")
    private String age;


    /**
     * 功能描述：获取配置中心上的数据
     *
     * @return 配置中心上的数据
     */
    @GetMapping("/v1/getNacosConfig")
    public String getNacosConfig() {
        return "【从 nacos配置中心 获取数据】id=" + id + ", name=" + name + ", age=" + age;
    }
}
