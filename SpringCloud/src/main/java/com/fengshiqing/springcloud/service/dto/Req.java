/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.dto;

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
public class Req {

    /**
     * 第几页
     */
    int pageNo;
    /**
     * 每页大小
     */
    int pageSize;

}
