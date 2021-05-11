/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller.Handler;

/**
 * 功能描述：自定义的业务异常，系统运行中的所有业务相关的异常。
 *
 * @author fengshiqing
 * @since 2020-08-23
 */
public class BizException extends Exception {

    private int exceptionCode;

    public BizException(int exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
