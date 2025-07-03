package com.kunning.springboot.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 功能描述：邮件服务。
 * <p>
 * 实现 ApplicationListener 接口，通过 E 泛型设置感兴趣的事件，监听指定类型事件并响应动作，实现 onApplicationEvent 方法，针对监听的 UserRegisterEvent 事件，进行自定义处理。
 *
 * @author 冯仕清
 * @since 2019-10-01
 */
@Slf4j
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    @Value("${spring.mail.username}")
    private String from;

    /**
     * Spring 自带的邮件Sender，会自动读取 application.properties 中的配置
     */
    private final JavaMailSender javaMailSender;

    /**
     * 构造器
     */
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 功能描述：监听到事件后，执行后续逻辑。可以直接监听 UserRegisterEvent 事件的，如果需要监听多种事件，就直接监听接口 ApplicationEvent。
     * 子类发放尽量别直接监听 ApplicationEvent，会吧别的事件页监听过来。
     * 最好是使用注解方式监听事件，CouponService#addCoupon(UserRegisterEvent)，这样还能实现一个监听多个事件的功能。实现接口就只能监听一个事件。
     *
     * @param event 事件
     */
    @Override
    @Async // 还可以弄成异步的，提高接口响应时间
    // public void onApplicationEvent(ApplicationEvent event) { // 尽量别直接监听父类型 ApplicationEvent，会吧别的事件页监听过来。
    public void onApplicationEvent(@NonNull UserRegisterEvent event) {
        log.info("【onApplicationEvent】【注册成功，给用户({}) 发送注册成功的邮件】", event.getUsername());
    }

    /**
     * 功能描述：发送文本邮件。
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param text    邮件内容
     */
    public final void sendSimpleMail(String to, String subject, String text) {
        log.info("【sendSimpleMail】【start】【to:{}, subject:{}, text:{}】", to, subject, text);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); // 纯文本邮件
        simpleMailMessage.setFrom(from); // 发送人
        simpleMailMessage.setTo(to); // 接收人
        simpleMailMessage.setSubject(subject); // 邮件主题
        simpleMailMessage.setText(text); // 邮件内容
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.error("【sendSimpleMail】【邮件发送失败】", e);
        }
        log.info("【sendSimpleMail】【end】");
    }

    /**
     * 功能描述：发送html邮件。
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public final void sendHtmlMail(String to, String subject, String content) {
        log.info("【sendHtmlMail】【start】【to:{}, subject:{}, content:{}】", to, subject, content);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true表示需要创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException | MailException e) {
            log.error("【sendHtmlMail】【发生异常】", e);
        }
        log.info("【sendHtmlMail】【end】");
    }

    /**
     * 功能描述：发送带附件的邮件。
     *
     * @param to       接收人
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件路径
     */
    public final void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        log.info("【sendAttachmentsMail】【start】【to:{}, subject:{}, content:{}, filePath:{}】", to, subject, content,
                filePath);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true表示需要创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file); // 多个附件的话，是不是需要多次调用这个方法？
            // mimeMessage.addAttachment("test"+fileName, file);

            javaMailSender.send(message);
        } catch (MessagingException | MailException e) {
            log.error("【sendAttachmentsMail】【发生异常】", e);
        }
        log.info("【sendAttachmentsMail】【end】");
    }

    /**
     * 功能描述：发送正文中有静态资源（图片）的邮件。
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param rscPath 文件路径
     * @param rscId   rscId
     */
    public final void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        log.info("【sendInlineResourceMail】【start】【to:{}, subject:{}, content:{}, rscPath:{}, rscId:{}】", to, subject,
                content, rscPath, rscId);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("【sendInlineResourceMail】【发生异常】", e);
        }
        log.info("【sendInlineResourceMail】【end】");
    }
}
