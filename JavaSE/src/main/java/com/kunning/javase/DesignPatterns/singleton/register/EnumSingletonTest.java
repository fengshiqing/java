/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.register;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

/**
 * 功能描述：枚举式单例。
 *
 * @author fengshiqing
 * @since 2021-05-04
 */
public class EnumSingletonTest {

    @Test
    public void test() {
        EnumSingleton instance = EnumSingleton.getInstance();
        instance.setData(new Object());

        try {
            Class<?> clazz = EnumSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(String.class, int.class);
            c.setAccessible(true);
            Object o = c.newInstance(); // 调用这个方法时，JDK会判断是否为枚举，枚举就会报错。
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
