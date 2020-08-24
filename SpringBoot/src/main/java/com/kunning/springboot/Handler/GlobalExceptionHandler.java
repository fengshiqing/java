/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Component
@ResponseBody
@ControllerAdvice // 该注解可以把异常处理器应用到所有控制器，而不是单个控制器。借助该注解，我们可以实现：在独立的某个地方，比如单独一个类，定义一套对各种异常的处理机制，然后在类的签名加上注解@ControllerAdvice，统一对 不同阶段的、不同异常 进行处理。这就是统一异常处理的原理。
public class GlobalExceptionHandler {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MyException.class)
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {

        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());

        return r;
    }

    public static class ErrorInfo<T> {

        public static Integer OK = 0;

        public static Integer ERROR = 100;

        private Integer code;

        private String message;

        private String url;

        private T data;

    // 省略getter和setter


        public static Integer getOK() {
            return OK;
        }

        public static Integer getERROR() {
            return ERROR;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}
