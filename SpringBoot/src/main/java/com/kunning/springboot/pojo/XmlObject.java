package com.kunning.springboot.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 */
@XStreamAlias("XmlObject")
public class XmlObject {

    @XStreamAlias("param_1")
    private String param_1;
    @XStreamAlias("param_2")
    private String param_2;
    @XStreamAlias("param_3")
    private String param_3;
    @XStreamAlias("param_4")
    private String param_4;

    /**
     * 构造函数
     */
    public XmlObject(){
    }

    /**
     * 构造函数
     */
    public XmlObject(String param_1, String param_2, String param_3, String param_4){
        this.param_1 = param_1;
        this.param_2 = param_2;
        this.param_3 = param_3;
        this.param_4 = param_4;
    }

    public String getParam_1() {
        return param_1;
    }

    public void setParam_1(String param_1) {
        this.param_1 = param_1;
    }

    public String getParam_2() {
        return param_2;
    }

    public void setParam_2(String param_2) {
        this.param_2 = param_2;
    }

    public String getParam_3() {
        return param_3;
    }

    public void setParam_3(String param_3) {
        this.param_3 = param_3;
    }

    public String getParam_4() {
        return param_4;
    }

    public void setParam_4(String param_4) {
        this.param_4 = param_4;
    }

    @Override
    public String toString() {
        return "XmlObject{" +
                "param_1='" + param_1 + '\'' +
                ", param_2='" + param_2 + '\'' +
                ", param_3='" + param_3 + '\'' +
                ", param_4='" + param_4 + '\'' +
                '}';
    }
}
