/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.controller;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import com.kunning.springboot.MyApplication;

@SpringJUnitConfig
@SpringBootTest(classes = MyApplication.class) // 加载springboot的测试环境配置
class HelloControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void hello() {
    }

    @Test
    void helloAngular() {
        Map<String, String> resp = restTemplate.getForObject("http://127.0.0.1:8080/angular/hello", Map.class);
        System.out.println(resp);
        Map<String, String> resp1 = restTemplate.postForObject("http://127.0.0.1:8080/angular/hello", "", Map.class);
        System.out.println(resp1);
    }

    @Test
    void hello1() {
    }

    @Test
    void hello2() {
    }

    @Test
    void hello3() {
    }

    @Test
    void hello111() {
    }

    @Test
    void helloAdmin() {
    }

    @Test
    void helloUser() {
    }

    @Test
    void session() {
    }

}