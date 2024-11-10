/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.controller.handler;

import com.fengshiqing.common.bean.Resp;
import com.fengshiqing.springcloud.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：全局统一异常处理。
 * <p>
 * ControllerAdvice 注解 可以把异常处理器应用到所有控制器，而不是单个控制器。借助该注解，我们可以实现：
 * 在独立的某个地方，比如单独一个类，定义一套对各种异常的处理机制，然后在类上使用注解@ControllerAdvice，统一对 不同阶段的、不同异常 进行处理。这就是统一异常处理的原理。
 *
 * @author fengshiqing
 * @since 2020-08-24
 */
@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Resp handleException(Exception e) {
        log.error("【统一异常处理 Exception】", e);
        return new Resp(500, I18nUtil.getMessage("system.error"));
    }

    @ExceptionHandler(BizException.class)
    public Resp handleBizException(BizException e) {
        log.error("【统一异常处理 BizException】", e);
        return new Resp(e.getExceptionCode(), e.getMessage());
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Resp handleBizException(HttpRequestMethodNotSupportedException e) {
        log.error("【统一异常处理 HttpRequestMethodNotSupportedException】", e);
        return new Resp(400001, e.getMessage());
    }


    /**
     * 这个是处理 方法参数为 实体类 的校验的异常
     * @param e MethodArgumentNotValidException
     * @return Resp
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Resp handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("【统一异常处理 MethodArgumentNotValidException】", e);
        List<String> list = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            list.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        }
        return new Resp(400002, String.join(" " + System.lineSeparator(), list));
    }

    /**
     * 这个是处理 方法参数为 非实体类 的校验的异常
     * @param e MethodArgumentNotValidException
     * @return Resp
     */
    @ExceptionHandler(NoSuchMessageException.class)
    public Resp handleNoSuchMessageException(NoSuchMessageException e) {
        log.error("【统一异常处理 NoSuchMessageException】", e);
        return new Resp(400003, e.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public Resp handleDateTimeParseException(DateTimeParseException e) {
        log.error("【统一异常处理 DateTimeParseException】", e);
        return new Resp(400004, I18nUtil.getMessage("system.error"));
    }

    @ExceptionHandler(IOException.class)
    public Resp handleIOException(IOException e) {
        log.error("【统一异常处理 IOException】", e);
        return new Resp(500005, e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public Resp handleSqlException(SQLException e) {
        log.error("【统一异常处理 SQLException】", e);
        return new Resp(500015, e.getMessage());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public Resp handleSqlException(BadSqlGrammarException e) {
        log.error("【统一异常处理 BadSqlGrammarException】", e);
        return new Resp(500025, I18nUtil.getMessage("system.error"));
    }

}
