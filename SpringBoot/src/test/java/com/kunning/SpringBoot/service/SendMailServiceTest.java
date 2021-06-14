package com.kunning.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.kunning.springboot.Application;

@SpringJUnitConfig
@SpringBootTest(classes = Application.class)
class SendMailServiceTest {

    @Autowired
    SendMailService sendMailService;

    @Test
    void sendSimpleMail() {
        sendMailService.sendSimpleMail("938481169@qq.com", "938481169@qq.com", "【邮件主题】", "【邮件内容】");
    }
}