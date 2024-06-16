/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller.response;

import com.kunning.springcloud.utils.I18nUtil;
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
        super(200, I18nUtil.getMessage("biz.operate.success"));
        this.data = data;
    }

    public RespData(int rtnCode, String rtnDesc, T data) {
        super(rtnCode, rtnDesc);
        this.data = data;
    }
}
