/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller;

import com.kunning.springboot.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RunWith(SpringRunner.class) //测试运行在spring环境下
@SpringBootTest(classes = Application.class) //加载springboot的测试环境配置
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

    @Test
    void invokeServiceComb() {
    }
}