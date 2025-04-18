/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.startup;

import javax.annotation.PostConstruct;

/**
 * 功能描述：这个好现实模板模式的写法吧
 */
public class MqAsynTaskHandler extends MqBaseHandler {

    @PostConstruct
    public void init() {
        super.process();
    }

    public void doService() {
        // 业务逻辑

    }

}
