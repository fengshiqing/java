/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springai.model;

/**
 * 功能描述：业务异常
 * @author 冯仕清
 * @since 2026-01-01
 */
public class BizException extends RuntimeException {

    private Integer code;

    private String message;

    public BizException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public BizException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    public BizException(Throwable e) {
        super(e);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 重写fillInStackTrace 业务异常不需要堆栈信息，提高效率.
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
