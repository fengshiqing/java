
/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller.response;

/**
 * 功能描述：响应状态码。
 *
 * @author 冯仕清
 * @since 2020-05-31
 */
public class ResponseApi {

    /**
     * 状态码
     */
    private int rtnCode = 200;

    /**
     * 状态描述
     */
    private String rtnMsg = "成功";

    /**
     * 构造函数
     */
    public ResponseApi() {
    }

    public ResponseApi(int rtnCode, String rtnMsg) {
        this.rtnCode = rtnCode;
        this.rtnMsg = rtnMsg;
    }

    public int getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(int rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    @Override
    public String toString() {
        return "ResponseApi{" +
                "rtnCode=" + rtnCode +
                ", rtnMsg='" + rtnMsg + '\'' +
                '}';
    }
}
