package com.kunning.SpringBoot.utils;

import com.kunning.springboot.utils.SendEmail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendEmailTest {

    @Test
    public void sendEmail() {
        SendEmail.sendEmail("938481169@qq.com", "938481169@qq.com", "【邮件主题】", "【邮件正文】");
    }
}