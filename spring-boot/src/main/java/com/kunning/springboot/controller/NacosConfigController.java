/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.controller;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//配置发布之后，动态刷新配置
@RestController
//@NacosConfigurationProperties(dataId = "MyApplication.properties", autoRefreshed = true)
public class NacosConfigController {

    @NacosValue(value="${user.id}", autoRefreshed=true)
    private String id;
    @NacosValue(value="${user.name}", autoRefreshed=true)
    private String name;
    @NacosValue(value="${user.age}", autoRefreshed=true)
    private String age;
    @NacosConfig(dataId = "MyApplication.properties", group = "DEFAULT_GROUP", key = "aa")
    private String aa;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    /**
     * 功能描述：获取服务实例。
     *
     * @return 服务实例列表
     */
//    @GetMapping("/getInstances")
//    public List<ServiceInstance> getInstances() {
//        return discoveryClient.getInstances("CloudApplication");
//    }

    @GetMapping("/getNacosConfig")
    public String providerTest() {
        return "【从 nacos配置中心 获取数据：aa=" + aa;
    }
}
