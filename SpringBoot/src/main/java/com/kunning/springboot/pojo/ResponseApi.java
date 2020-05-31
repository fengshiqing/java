
package com.kunning.springboot.pojo;

/**
 * 功能描述：响应数据。
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
    private String retMsg = "成功";

    /**
     * 构造函数
     */
    public ResponseApi() {
    }

    public ResponseApi(int rtnCode, String retMsg) {
        this.rtnCode = rtnCode;
        this.retMsg = retMsg;
    }

    public int getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(int rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    @Override
    public String toString() {
        return "ResponseApi{" +
                "rtnCode=" + rtnCode +
                ", retMsg='" + retMsg + '\'' +
                '}';
    }
}
