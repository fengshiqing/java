/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.controller.handler;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述：自定义的业务异常，系统运行中的所有业务相关的异常。
 *
 * @author fengshiqing
 * @since 2020-08-23
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    private int exceptionCode;

    public BizException(int exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
