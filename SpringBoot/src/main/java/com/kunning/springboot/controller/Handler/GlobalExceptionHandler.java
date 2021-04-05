/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller.Handler;

import com.kunning.springboot.controller.response.ResponseApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 功能描述：全局统一异常处理。
 *
 * ControllerAdvice 注解 可以把异常处理器应用到所有控制器，而不是单个控制器。借助该注解，我们可以实现：
 * 在独立的某个地方，比如单独一个类，定义一套对各种异常的处理机制，然后在类上使用注解@ControllerAdvice，统一对 不同阶段的、不同异常 进行处理。这就是统一异常处理的原理。
 *
 * @author fengshiqing
 * @since 2020-08-24
 */
@Component
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 功能描述：全局业务异常处理
     *
     * @param exception 异常信息
     *
     * @return ResponseApi对象
     */
    @ExceptionHandler(value = MyException.class)
    public ResponseApi dealMyException(MyException exception) {
        LOGGER.info("【统一处理业务异常】", exception);

        ResponseApi response = new ResponseApi();
        response.setRtnCode(exception.getExceptionCode());
        response.setRtnMsg(exception.getMessage());
        return response;
    }


    /**
     * 功能描述：全局IO异常处理
     *
     * @param e 异常信息
     *
     * @return ResponseApi对象
     */
    @ExceptionHandler(value = IOException.class)
    public ResponseApi dealIOException(IOException e) {
        LOGGER.info("【统一处理IO异常】", e);

        ResponseApi response = new ResponseApi();
        response.setRtnCode(500005);
        response.setRtnMsg(e.getMessage());
        return response;
    }


    /**
     * 功能描述：全局SQL异常处理
     *
     * @param e 异常信息
     *
     * @return ResponseApi对象
     */
    @ExceptionHandler(value = SQLException.class)
    public ResponseApi dealSqlException(IOException e) {
        LOGGER.info("【统一处理SQL异常】", e);

        ResponseApi response = new ResponseApi();
        response.setRtnCode(500015);
        response.setRtnMsg(e.getMessage());
        return response;
    }

}
