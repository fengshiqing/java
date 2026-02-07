/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.seriable;

import java.io.Serializable;

/**
 * 功能描述：序列化是否会被破坏单例。
 *
 * @author 冯仕清
 * @since 2021-05-04
 */
public class SeriableSingleton implements Serializable {

    // 序列化
    // 把内存中对象的状态转换为字节码的形式
    // 把字节码通过IO输出流，写到磁盘上
    // 永久保存下来，持久化

    // 反序列化
    // 将持久化的字节码内容，通过IO输入流读到内存中来
    // 转化成一个Java对象

    /**
     * 全局静态常量
     */
    public static final SeriableSingleton INSTANCE = new SeriableSingleton();

    /**
     * 私有构造器
     */
    private SeriableSingleton() {
    }

    /**
     * 功能描述：公有方法
     *
     * @return 单例对象
     */
    public static SeriableSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 功能描述：防止反序列化破环单例。 注意：必须这么写。修饰符、返回值、方法名等整个方法，必须一模一样。
     *
     * @return 单例对象
     */
    private Object readResolve() {
        return INSTANCE;
    }

}
