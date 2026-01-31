/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.kunning.springboot.model;

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


    /**
     * 重写fillInStackTrace 业务异常不需要堆栈信息，堆栈信息会降低性能。
     * 这里去掉堆栈信息后，在高并发场景下，大约能带来 10-50 倍的异常实例化性能提升。
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
