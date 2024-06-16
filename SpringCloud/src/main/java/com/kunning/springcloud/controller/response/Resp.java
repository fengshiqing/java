/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller.response;

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
@Getter
@Setter
@ToString
public class Resp {

    private int rtnCode;

    private String rtnDesc;

    public Resp(int rtnCode, String rtnDesc) {
        this.rtnCode = rtnCode;
        this.rtnDesc = rtnDesc;
    }
}
