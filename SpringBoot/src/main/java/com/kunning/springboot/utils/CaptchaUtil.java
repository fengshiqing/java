/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.common.Fonts;
import com.ramostear.captcha.support.CaptchaStyle;

/**
 * 功能描述：验证码工具
 *
 * @author fengshiqing
 * @since 2020-08-13
 */
public class CaptchaUtil {

    /**
     * 私有化构造函数
     */
    private CaptchaUtil(){
    }

    /**
     * 在默认情况下，生成的验证码以图片形式展现，包含09-az-A~Z的字符随机组合，字符长度为5，图片宽度为160，高度为50，字体为微软雅黑。
     *
     * @param request request
     * @param response response
     */
    public static void HappyCaptchaUtils(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response)
                //HappyCaptcha提供两种验证码展现形式：图片和动画。默认的展现形式为图片,可供选择的值有IMG和ANIM
                //若展现形式为图片，则style(CaptchaStyle.IMG)可以省略。
                .style(CaptchaStyle.ANIM)
                //length()方法用于设置验证码字符长度，默认情况下缺省值为5
                .length(6)
                //width()方法可对验证码图片的宽度进行调节，默认的缺省值为160
                .width(180)
                //同width()方法一样，height()方法用于设置验证码图片的高度，默认缺省值为50
                .height(60)
                //默认缺省字体为微软雅黑 内置了四种字体
                .font(Fonts.getInstance().zhFont())
                .build().finish();
    }

    /**
     * 功能描述：当验证码被使用后，通过此方法将Session中存放的验证码清理掉。
     * HappyCaptcha验证码的Key为“happy-captcha”。
     *
     * @param request request
     */
    public static void removeCaptcha(HttpServletRequest request) {
        HappyCaptcha.remove(request);
    }

}
