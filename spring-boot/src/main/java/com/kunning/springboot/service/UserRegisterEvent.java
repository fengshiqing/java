/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 功能描述：通过继承 ApplicationEvent，实现自定义事件。另外，通过它的 source 属性可以获取事件源，timestamp 属性可以获得发生时间。
 *
 * @author kunning
 * @since 2021-12-09
 */
@Getter
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public UserRegisterEvent(Object source, Clock clock, String username) {
        super(source, clock);
        this.username = username;
    }

}
