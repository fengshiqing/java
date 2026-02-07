/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：接口响应数据列表。
 *
 * @author fengshiqing
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString(callSuper = true)
public class RespDataList<T> extends Resp {

    private T data;

    private int totalCount;

    public RespDataList(T data, int totalCount) {
        super(200, null);
        this.data = data;
        this.totalCount = totalCount;
    }
}
