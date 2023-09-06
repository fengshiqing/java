/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunning.springboot.utils.CaptchaUtil;

/**
 * 功能描述：验证码
 *
 * @author fengshiqing
 * @since 2020-08-13
 */
@Controller
@ResponseBody
public class CaptchaController {

    @RequestMapping(value = "/HappyCaptcha", method = RequestMethod.GET)
    public void HappyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        //二次刷新的时候都进行清除
        CaptchaUtil.removeCaptcha(request);
        //生成验证码
        CaptchaUtil.HappyCaptchaUtils(request, response);
        //设置过期时间（1分半）
        request.getSession().setMaxInactiveInterval(90);
    }

}
