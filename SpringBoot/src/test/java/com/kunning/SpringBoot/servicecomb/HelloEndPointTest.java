/*
 * Copyright (c) 2020. fengshiqing 冯仕清
 */

package com.kunning.springboot.servicecomb;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import com.kunning.springboot.Application;

/**
 * 功能描述：微服务单元测试
 *
 * @author fengshiqing
 * @since 2020-08-09
 */
@SpringJUnitConfig
@SpringBootTest(classes = Application.class)
public class HelloEndPointTest {

    private final RestTemplate restTemplate = RestTemplateBuilder.create();

    @RpcReference(microserviceName = "ServiceComb_fengshiqing", schemaId = "hello")
    private HelloEndPoint helloEndPoint;

    @Test
    public void invokeServiceComb() {
        String str = helloEndPoint.hello();
        System.out.println(str);
    }

    @Test
    public void invokeHello() {
        //service url is : cse://serviceName/operation
        String serviceName = "ServiceComb_fengshiqing";
        String rtnStr = restTemplate.getForObject("cse://" + serviceName + "/servicecomb/hello", String.class);
        System.out.println(rtnStr);
    }

}