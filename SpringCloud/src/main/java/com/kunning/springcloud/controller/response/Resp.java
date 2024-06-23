/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：接口响应值。
 *
 * @author fengshiqing
 * @since 2022-04-23
 */
@Getter
@Setter
@ToString
public class Resp {

    private int code;

    private String message;

    public Resp(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
