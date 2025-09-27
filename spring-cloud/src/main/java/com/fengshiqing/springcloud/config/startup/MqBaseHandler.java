/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config.startup;

/**
 * 功能描述：模板模式的父类代码
 */
public abstract class MqBaseHandler {

    public void process() {
        doService();
    }

    public abstract void doService();

}
