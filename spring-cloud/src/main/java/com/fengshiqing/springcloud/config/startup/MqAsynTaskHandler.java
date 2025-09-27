/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config.startup;

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
