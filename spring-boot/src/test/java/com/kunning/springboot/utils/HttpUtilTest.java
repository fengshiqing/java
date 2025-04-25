/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class HttpUtilTest {

    @Test
    public void doGet() {
        String respStr = HttpUtil.doGet("http://whois.pconline.com.cn/ipJson.jsp?json=true");
        System.out.println("【respStr：】" + respStr);
    }

    @Test
    public void doPost() {
        String respStr = HttpUtil.doPost("http://whois.pconline.com.cn/ipJson.jsp?json=true", "");
        System.out.println("【respStr：】" + respStr);
    }

}