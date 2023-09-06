/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.controller;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
// import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//配置发布之后，动态刷新配置
// @RefreshScope
// @RestController
// @NacosPropertySource(dataId = "example", autoRefreshed = true)
// @NacosPropertySource(dataId = "CloudApplication.properties", autoRefreshed = true)
// @NacosConfigurationProperties(dataId = "CloudApplication.properties", autoRefreshed = true)
public class NacosConfigController {

    @NacosValue(value="${user.id}", autoRefreshed=true)
    private String id;
    @NacosValue(value="${user.name}", autoRefreshed=true)
    private String name;
    @NacosValue(value="${user.age}", autoRefreshed=true)
    private String age;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 功能描述：获取服务实例。
     *
     * @return 服务实例列表
     */
    @GetMapping("/getInstances")
    public List<ServiceInstance> getInstances() {
        return discoveryClient.getInstances("CloudApplication");
    }

    @GetMapping("/getNacosConfig")
    public String providerTest() {
        return "从 nacos配置中心 获取数据：id=" + id + ", name=" + name + ", age=" + age;
    }
}
