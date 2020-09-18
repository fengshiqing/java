/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.Handler;

/**
 * 功能描述：自定义异常
 *
 * @author fengshiqing
 * @since 2020-08-23
 */
public class MyException extends Exception {

    private int exceptionCode;

    public MyException(int exceptionCode, String message) {
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
