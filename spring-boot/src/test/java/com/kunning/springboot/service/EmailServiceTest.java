/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kunning.springboot.MyApplication;

@SpringJUnitConfig
@SpringBootTest(classes = MyApplication.class)
class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    void sendSimpleMail() {
        emailService.sendSimpleMail("938481169@qq.com", "【邮件主题】", "【邮件内容】");
    }

    @Test
    public void testSimpleMail() {
        emailService.sendSimpleMail("938481169@qq.com", "test simple mail", " hello this is simple mail");
    }

    @Test
    public void testHtmlMail() {
        String content = """
                <html>
                <body>
                    <h3>hello world ! 这是一封html邮件!</h3>
                </body>
                </html>""";
        emailService.sendHtmlMail("938481169@qq.com", "test simple mail", content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "e:\\tmp\\application.log";
        emailService.sendAttachmentsMail("938481169@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "picture001";
        String content = "<html><body>这是有图片的邮件：<img src='cid:" + rscId + "' ></body></html>";
        String imgPath = "C:\\Users\\kunning\\Pictures\\算了不生气.png";
        emailService.sendInlineResourceMail("938481169@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() {
        // 创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);
        emailService.sendHtmlMail("938481169@qq.com", "主题：这是模板邮件", emailContent);
    }
}