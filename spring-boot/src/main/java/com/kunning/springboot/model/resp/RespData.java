/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.model.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：接口响应数据。
 *
 * @author fengshiqing
 * @since 2022-05-10
 */
@Getter
@Setter
@ToString(callSuper = true)
public class RespData<T> extends Resp {

    private T data;

    public RespData(T data) {
        super(200, null); // 响应成功，就不需要给message说明了
        this.data = data;
    }

}
