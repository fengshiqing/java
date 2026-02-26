/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.model.dto;

import lombok.Data;

/**
 * @Title: QueryFileDTO
 * @author 冯仕清
 * @Package com.fengshiqing.springai.pojo.dto
 * @Date 2025/2/8 21:31
 * @description: 查找文件dto
 */
@Data
public class QueryFileDTO {
    private Integer page;
    private Integer pageSize;
    private String fileName;
}
