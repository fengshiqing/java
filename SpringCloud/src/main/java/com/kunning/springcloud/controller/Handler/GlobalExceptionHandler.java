/*
 *  Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller.Handler;

import com.fengshiqing.common.bean.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

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

    @ExceptionHandler(value = Exception.class)
    public Resp handleException(Exception exception) {
        log.error("【统一处理Exception】", exception);
        // TODO 管理员是谁，需要留一个联系方式
        return new Resp(500, "【系统发生异常，请稍后再试，或者直接联系管理员】");
    }

    @ExceptionHandler(value = BizException.class)
    public Resp handleBizException(BizException exception) {
        log.error("【统一处理BizException】", exception);
        return new Resp(exception.getExceptionCode(), exception.getMessage());
    }

    @ExceptionHandler(value = IOException.class)
    public Resp handleIOException(IOException e) {
        log.error("【统一处理IOException】", e);
        return new Resp(500005, e.getMessage());
    }

    @ExceptionHandler(value = SQLException.class)
    public Resp handleSqlException(SQLException e) {
        log.error("【统一处理SQLException】", e);
        return new Resp(500015, e.getMessage());
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Resp handleSqlException(BadSqlGrammarException e) {
        log.error("【统一处理BadSqlGrammarException】", e);
        return new Resp(500015, "【系统发生异常，请稍后再试，或者直接联系管理员】");
    }

}
