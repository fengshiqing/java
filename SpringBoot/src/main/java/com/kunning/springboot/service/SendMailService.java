package com.kunning.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 功能描述：邮件服务类
 *
 * @author 冯仕清
 * @since 2019-10-01
 */
@Service
public class SendMailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailService.class);

    /**
     * Spring 自带的邮件Sender，会自动读取 application.properties 中的配置
     */
    private final JavaMailSender javaMailSender;

    /**
     * 构造器
     */
    public SendMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 功能描述：发送电子邮件。
     *
     * @param from    发送人
     * @param to      接收人
     * @param subject 邮件主题
     * @param text    邮件内容
     */
    public void sendSimpleMail(String from, String to, String subject, String text) {
        LOGGER.info("【sendSimpleMail】【start】【from:{}, to:{}, subject:{}】", from, to, subject);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from); // 发送人
        simpleMailMessage.setTo(to); // 接收人
        simpleMailMessage.setSubject(subject); // 邮件主题
        simpleMailMessage.setText(text); // 邮件内容
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            LOGGER.error("【sendSimpleMail】【邮件发送失败】", e);
        }
        LOGGER.info("【sendSimpleMail】【end】【发送邮件成功】");
    }

}
