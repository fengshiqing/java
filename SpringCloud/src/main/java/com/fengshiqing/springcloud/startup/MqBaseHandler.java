/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.startup;

/**
 * 功能描述：模板模式的父类代码
 */
public abstract class MqBaseHandler {

    public void process() {
        doService();
    }

    public abstract void doService();

}
