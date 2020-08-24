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

    public MyException(String message) {
        super(message);
    }

}
