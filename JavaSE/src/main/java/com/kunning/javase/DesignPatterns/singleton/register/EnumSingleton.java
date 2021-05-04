package com.kunning.javase.DesignPatterns.singleton.register;

/**
 * 功能描述：枚举式单例。
 * 这是最最最优雅的一种写法。JDK的底层就对定了“不允许用反射创建枚举的实例对象”
 * 缺点：这种方式实际上也是一种饿汉式单例，默写情况下，存在一定程度上的内存浪费。
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
