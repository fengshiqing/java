/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：服务降级后，返回此响应。
 *
 * @author fengshiqing
 * @since 2025-04-20
 */
@RestController
public class DowngradeController {

    @RequestMapping(value = "/downgrade", produces = "text/html;charset=UTF-8")
    public String downgrade() {
        return "<html><body><div style='width:800px; margin:auto; text-align:center; font-size:24px'>" +
                "Gateway返回：服务器忙，请稍后重试！" +
                "</div></body></html>";
    }

}
