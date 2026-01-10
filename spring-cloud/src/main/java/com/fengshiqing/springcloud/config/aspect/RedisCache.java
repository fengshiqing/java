/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：redis缓存切面。查询数据时，先从redis中获取。
 *
 * @author fengshiqing
 * @since 2020-08-23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    String key();

    /**
     * 默认失效时间为5分钟
     */
    long expire() default 300;

    /**
     * 默认失效时间的单位：秒
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
