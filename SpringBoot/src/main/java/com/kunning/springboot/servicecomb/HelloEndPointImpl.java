/*
 * Copyright (c) 2020. fengshiqing 冯仕清
 */

package com.kunning.springboot.servicecomb;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：微服务
 *
 * @author fengshiqing
 * @since 2020-08-09
 */
@RestSchema(schemaId = "hello")
@RequestMapping(path = "/servicecomb") // 这行代码是微服务必须的，且参数不能为空，否子微服务启动失败！
public class HelloEndPointImpl implements HelloEndPoint {

    @Override
    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello servicecomb!";
    }

}
