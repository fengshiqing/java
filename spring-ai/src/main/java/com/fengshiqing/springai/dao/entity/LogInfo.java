/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * 
 */
@Data
public class LogInfo {
    /**
     * id
     */
    private Long id;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 类目
     */
    private String className;

    /**
     * 请求时间戳
     */
    private Date requestTime;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应结果
     */
    private String response;
}