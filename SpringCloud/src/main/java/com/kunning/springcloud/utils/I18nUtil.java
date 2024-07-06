/*
 *  Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.utils;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 功能描述：国际化的工具类。
 * 封装国际化文本的读取接口，主要方便在代码中使用，不需要每次 @Autowired 注入 MessageSource 来使用。
 *
 * @author fengshiqing 冯仕清
 * @since 2024-06-01
 */
@Slf4j
@Component
public class I18nUtil implements ApplicationContextAware {

    private static MessageSource messageSource;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    /**
     * 功能描述：获取国际化message
     *
     * @param msgKey 配置的key
     * @return 国际化文本
     */
    public static String getMessage(String msgKey) {
        return messageSource.getMessage(msgKey, null, null, LocaleContextHolder.getLocale());
    }

    /**
     * 功能描述：获取国际化message
     *
     * @param msgKey 配置的key
     * @param args   占位参数
     * @return 国际化文本
     */
    public static String getMessage(String msgKey, @Nullable Object[] args) {
        return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
    }

}