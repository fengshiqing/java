/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.dao.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 
 */
@Data
public class SensitiveCategory {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private LocalDate createdTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    /**
     * 状态
     */
    private String status;
}