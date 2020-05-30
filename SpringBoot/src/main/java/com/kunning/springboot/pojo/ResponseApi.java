
package com.kunning.springboot.pojo;

/**
 * 功能描述：响应数据。
 *
 * @param <T> 返回个客户端的具体数据
 */
public class ResponseApi<T> {

    /**
     * 状态码
     */
    private int code = 200;

    /**
     * 状态描述
     */
    private String msg = "成功";

    /**
     * 返回给客户端的详情数据
     */
    private T data;

    /**
     * 构造函数
     */
    public ResponseApi() {
    }

    public ResponseApi(T data) {
        this.data = data;
    }

    public ResponseApi(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseApi(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseApi{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
