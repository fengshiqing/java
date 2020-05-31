package com.kunning.springboot.pojo;

/**
 * 功能描述：响应码。
 *
 * @author 冯仕清
 * @since 2020-05-31
 */
public class ResponseDataApi<T> extends ResponseApi {

    /**
     * 返回给客户端的详情数据
     */
    private T rtnData;

    public ResponseDataApi() {
    }

    public ResponseDataApi(T rtnData) {
        super(200, "成功");
        this.rtnData = rtnData;
    }

    public T getRtnData() {
        return rtnData;
    }

    public void setRtnData(T rtnData) {
        this.rtnData = rtnData;
    }

    @Override
    public String toString() {
        return "ResponseDataApi{" +
                "rtnData=" + rtnData +
                '}';
    }
}
