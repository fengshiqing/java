/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 功能描述：优惠券服务。
 * 不同于上面的实现 ApplicationListener 接口方式，在方法上，添加 @EventListener 注解，并设置监听的事件为 UserRegisterEvent。这是另一种使用方式。
 *
 * @author 冯仕清
 * @since 2021-12-09
 */
@Service
public class CouponService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CouponService.class);

    @EventListener// <1>
    public void addCoupon(UserRegisterEvent event) {
        LOGGER.info("【addCoupon】【给用户({}) 发放优惠劵】", event.getUsername());
    }
}
