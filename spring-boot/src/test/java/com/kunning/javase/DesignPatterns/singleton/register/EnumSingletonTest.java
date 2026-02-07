/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.register;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 功能描述：枚举式单例。
 *
 * @author 冯仕清
 * @since 2021-05-04
 */
public class EnumSingletonTest {

    /**
     * 功能描述：测试枚举式单例能否被“反射”破坏.
     * 测试结果：枚举天然的能防止 “反射” 导致的多个实例。
     */
    @Test
    public void test() {
        EnumSingleton instance = EnumSingleton.getInstance();
        instance.setData(new Object());

        try {
            Class<?> clazz = EnumSingleton.class;
            Constructor<?> c = clazz.getDeclaredConstructor(String.class, int.class);
            c.setAccessible(true);
            Object o = c.newInstance(); // 调用这个方法时，JDK会判断是否为枚举，枚举就会报错。
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述：测试枚举式单例能否被“序列化/饭序列化”破坏.
     * 测试结果：枚举天然的能防止 “序列化/反序列化” 导致的多个实例。
     */
    @Test
    public void testEnumSingleton() {

        EnumSingleton s1;
        EnumSingleton s2 = EnumSingleton.getInstance();

        FileOutputStream fos;
        try {

            fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (EnumSingleton) ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
