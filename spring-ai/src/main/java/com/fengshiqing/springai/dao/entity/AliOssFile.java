/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 
 */
@Data
@Builder
public class AliOssFile {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 该文件分割出的多段向量文本ID
     */
    private String vectorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}