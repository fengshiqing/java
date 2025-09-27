/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.lazy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

public class ReflectTest {

    /**
     * 功能描述：测试 双重检查加锁单例 是否会被 反射 破坏
     */
    @Test
    public void testDoubleCheckSingleton() {
        try {
            Object instance1 = LazyDoubleCheckSingleton.getInstance();

            Class<?> clazz = LazyDoubleCheckSingleton.class;
            Constructor<?> c = clazz.getDeclaredConstructor();
            c.setAccessible(true);

            Object instance2 = c.newInstance(); // 通过反射创造出对象，每次都是一个全新的对象
            Object instance3 = c.newInstance(); // 通过反射创造出对象，每次都是一个全新的对象

            System.out.println(instance1);
            System.out.println(instance2);
            System.out.println(instance3);

            System.out.println(instance1 == instance2);
            System.out.println(instance2 == instance3);
        } catch (Exception e) {
            System.out.println("异常：" + e);
        }
    }

    /**
     * 功能描述：测试 静态内部类单例 是否会被 反射 破坏
     */
    @Test
    public void testStaticInnerClassSingleton() {
        try {
            Object instance1 = LazyStaticInnerClassSingleton.getInstance();

            Class<?> clazz = LazyStaticInnerClassSingleton.class;
            Constructor<?> c = clazz.getDeclaredConstructor();
            c.setAccessible(true);

            Object instance2 = c.newInstance(); // 通过反射创造出对象，每次都是一个全新的对象，这样会破坏单例，需要在构造函数中做判断
            Object instance3 = c.newInstance(); // 通过反射创造出对象，每次都是一个全新的对象

            System.out.println(instance1);
            System.out.println(instance2);
            System.out.println(instance3);

            System.out.println(instance1 == instance2);
            System.out.println(instance2 == instance3);
        } catch (Exception e) {
            System.out.println("异常：" + e);
        }
    }

}
