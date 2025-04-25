/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import org.junit.jupiter.api.Test;

class SendEmailTest {

    @Test
    public void sendEmail() {
        SendEmail.sendEmail("938481169@qq.com", "938481169@qq.com", "【邮件主题】", "【邮件正文】");
    }
}