/*
 * Copyright (c) 2020. fengshiqing 冯仕清
 */

package com.kunning.springboot.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

}
