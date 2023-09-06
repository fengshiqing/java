/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：接口响应值。
 *
 * @author fengshiqing
 * @since 2022-04-23
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Resp {

    private int rtnCode;

    private String rtnDesc;
}
