/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：接口响应值。
 *
 * @author fengshiqing
 * @since 2022-04-23
 */
@NoArgsConstructor // 无参构造器，openFeign默认需要无参构造器
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Resp {

    private int code;

    private String message;

}
