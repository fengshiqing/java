package com.kunning.javase.DesignPatterns.singleton.register;

/**
 * 功能描述：枚举式单例。
 *
 * @author fengshiqing
 * @since 2021-05-04
 */
public enum EnumSingleton {

    /**
     * 单例对象的引用
     */
    INSTANCE;

    /**
     * 单例对象中的其他数据
     */
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
