/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.dao.entity;

import lombok.Data;

/**
 * 
 */
@Data
public class SensitiveWord {
    /**
     * id
     */
    private Integer id;

    /**
     * 敏感词内容
     */
    private String word;

    /**
     * 敏感词类别
     */
    private String category;

    /**
     * 敏感词状态
     */
    private String status;

    /**
     * 创建时间戳
     */
    private String createdAt;

    /**
     * 更新时间戳
     */
    private String updatedAt;
}