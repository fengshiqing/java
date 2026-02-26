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
public class WordFrequency {
    /**
     * id
     */
    private Integer id;

    /**
     * 分词
     */
    private String word;

    /**
     * 出现频次
     */
    private Integer countNum;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}