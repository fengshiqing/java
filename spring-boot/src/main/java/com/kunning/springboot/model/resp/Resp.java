/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.model.resp;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.MDC;

/**
 * 功能描述：接口响应。
 *
 * @author 冯仕清
 * @since 2022-04-23
 */
@NoArgsConstructor // 无参构造器，openFeign默认需要无参构造器
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Resp {

    private int code;

    // 有的系统用 msg，有的用 message，这里两个 属性都定义下。
    @JsonAlias({"msg", "message"}) // 这个注解只会对入参其起作用，出参中的属性永远都是 message！
    private String message;

    private String traceId = MDC.get("traceId");

}
