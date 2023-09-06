/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：接口响应数据。
 *
 * @author fengshiqing
 * @since 2022-05-10
 */
@Getter
@Setter
@ToString
public class RespData<T> extends Resp {

    private T data;

    public RespData(int rtnCode, String rtnDesc, T data) {
        super(rtnCode, rtnDesc);
        this.data = data;
    }
}
