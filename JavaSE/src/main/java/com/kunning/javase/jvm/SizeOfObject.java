/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.jvm;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 功能描述：测试对象占用内存的大小。
 *
 * @author kunning
 * @since 2021-05-05
 **/
public class SizeOfObject {

    int[] arr1 = {};
    int[] arr2 = {};

    @Test
    public void testObjectSize() {
        Object object = new Object();

        // 打印对象占用内存大小，单位：字节
        // -XX:+UseCompressedOops 开启压缩
        System.out.println(ClassLayout.parseInstance(object) + "字节Byte");
        // 打印内存布局。
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        System.out.println("===");

        SizeOfObject object2 = new SizeOfObject();
        // 打印对象占用内存大小，单位：字节
        System.out.println(ClassLayout.parseInstance(object2) + " 字节Byte");
        // 打印内存布局。
        System.out.println(ClassLayout.parseInstance(object2).toPrintable());
    }

}
