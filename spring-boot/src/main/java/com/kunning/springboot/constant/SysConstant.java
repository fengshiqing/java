/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.constant;

/**
 * 功能描述：用户相关的常量。
 *
 * @author 冯仕清
 * @since 2024-09-25
 */
public interface SysConstant {

    /**
     * 日志链路追踪
     */
    String TRACE_ID = "TRACE_ID";

    /**
     * 日志链路追踪
     */
    String USER_NAME = "USER_NAME";

    /**
     * 性别
     */
    interface GENDER {
        /**
         * 男
         */
        int gender_1 = 1;
        /**
         * 女
         */
        int gender_2 = 2;
    }

}
