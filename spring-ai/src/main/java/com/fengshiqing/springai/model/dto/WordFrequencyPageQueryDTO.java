/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.model.dto;

import lombok.Data;

@Data
public class WordFrequencyPageQueryDTO {
    private int page;
    private int pageSize;
    private String word;
    private String businessType;
    private Integer countNumMin;
}