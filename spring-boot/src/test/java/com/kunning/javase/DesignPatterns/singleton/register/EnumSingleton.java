/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.register;

/**
 * 功能描述：枚举式单例。
 * 这是最最最优雅的一种写法。
 * <p>
 * 优点：
 * 1、并发安全
 * 2、能防止 “反射” 导致的多个实例，JDK的底层就对定了“不允许用反射创建枚举的实例对象”
 * 3、能防止 “序列化/反序列化” 导致的多个实例
 * <p>
 * 缺点：
 * 唯一的缺点：这种方式实际上也是一种饿汉式单例，某些情况下，存在一定程度上的内存浪费。
 *
 * @author 冯仕清
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
