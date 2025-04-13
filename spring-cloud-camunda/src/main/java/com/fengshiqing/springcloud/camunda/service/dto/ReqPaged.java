/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：接口响应数据。
 *
 * @author fengshiqing
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
public class ReqPaged {

    /**
     * 第几页
     */
    @NotNull(message = "{biz.ReqPaged.pageNo.null}")
    @Min(value = 1, message = "{biz.ReqPaged.pageNo.error}")
    @Max(value = Integer.MAX_VALUE, message = "{biz.ReqPaged.pageNo.error}")
    private int pageNo;

    /**
     * 每页大小
     */
    @NotNull(message = "{biz.ReqPaged.pageSize.null}")
    @Min(value = 1, message = "{biz.ReqPaged.pageSize.error}")
    @Max(value = Integer.MAX_VALUE, message = "{biz.ReqPaged.pageSize.error}")
    private int pageSize;

}
