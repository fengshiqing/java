package com.kunning.springboot.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ResultData<T> {

    @Getter
    @Setter
    private int code = 200;

    @Getter
    @Setter
    private String msg = "成功";

    @Getter
    @Setter
    private T data;

    public ResultData() {
    }


    public ResultData(T data) {
        this.data = data;
    }
}
