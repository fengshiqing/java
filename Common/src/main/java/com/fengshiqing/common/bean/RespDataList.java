/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common.bean;

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

    public RespDataList(int rtnCode, String rtnDesc, T data, int totalCount) {
        super(rtnCode, rtnDesc);
        this.data = data;
        this.totalCount = totalCount;
    }
}
