/*
 *  Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.config;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * 功能描述：国际化相关的配置。
 * <p>
 * 由于 WebMvcAutoConfiguration 会注入一个默认的LocaleResolver，
 * 因此自定义的 LocaleConfig 要在WebMvcAutoConfiguration之前先执行，且 beanName 是 localeResolver
 * 目的就是用我们配置的 LocaleConfig 替换掉 WebMvcAutoConfiguration 自动注入的 LocaleConfig
 * <p>
 * 原文链接：<a href="https://blog.csdn.net/qq_47768542/article/details/137081939">...</a>
 */
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class I18nConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(I18nConfig.class);

    /**
     * 国际化消息源
     * 用于解析消息的策略接口，支持此类消息的参数化和国际化。根据Locale区域读取不同的properties国际化文件
     */
    @Resource
    private MessageSource messageSource;


    /**
     * 区域解析器，供消息源@MessageSource根据不同的区域@java.util.Locale读取不同的properties文件
     *
     * @return {@code LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        LOGGER.info("【init config】【localeResolver】");
        // AcceptHeaderLocaleResolver作用：通过请求头Accept-Language的值（zh-CN、en-US等）来改变当前的区域设置
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        // 设置默认区域：简体中文。Locale对象表示特定的地理、政治或文化区域，用以区分区域
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }


    /**
     * 使用自定义LocalValidatorFactoryBean，
     * 设置Spring国际化消息源，用户jsr303验证信息实现自定义国际化
     */
    @Bean
    public Validator getValidator() {
        LOGGER.info("【init config】【getValidator】");
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}

