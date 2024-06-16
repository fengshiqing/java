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
@ToString(callSuper = true)
public class RespData<T> extends Resp {

    private T data;

    public RespData(T data) {
        super(200, "操作成功");
        this.data = data;
    }

    public RespData(int rtnCode, String rtnDesc, T data) {
        super(rtnCode, rtnDesc);
        this.data = data;
    }
}
