package com.kunning.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 功能描述：发送E-mail。
 *
 * @author 冯仕清
 * @since 2019年11月16日 17点45分
 */
public class SendEmail {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmail.class);

    /**
     * 私有化构造函数
     */
    private SendEmail() {
    }

    /**
     * 功能描述：发送电子邮件
     *
     * @param from    发件人
     * @param to      收件人
     * @param subject 邮件主题
     * @param text    邮件正文
     */
    public static void sendEmail(String from, String to, String subject, String text) {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名，邮件服务器
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息

        Session session = Session.getDefaultInstance(properties); // 获取默认session对象
        Message message = new MimeMessage(session); // 创建默认的 MimeMessage 对象

        try {
            message.setFrom(new InternetAddress(from)); // Set From: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Set To: 头部头字段
            message.setSubject(subject); // 邮件主题
            message.setText(text); // 邮件正文

            Transport transport = session.getTransport();
            transport.connect(from, "vbmqwiykdpqgbcfb");// 发件人邮箱，密码为客户端授权码
            transport.sendMessage(message, message.getAllRecipients()); // 发送邮件
            transport.close();

            LOGGER.info("【sendEmail】【end】【发送邮件成功】");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
