/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.common.javase.jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 功能描述：测试对象占用内存的大小。
 * 这里说的比较清楚：<a href="https://www.cnblogs.com/katsu2017/p/12617098.html" />
 *
 * @author 冯仕清
 * @since 2021-05-05
 **/
public class SizeOfObject {

    // -XX:+UseCompressedClassPointers 开启对象头中的class point压缩
    // -XX:+UseCompressedOops 开启对象布局中的实例数据区中的指针引用的压缩
    // 可使用java -XX:+PrintCommandLineFlags -version 命令查看默认的或已设置的jvm参数

    /**
     * 功能描述：验证空对象占用的内存大小。
     */
    @Test
    public void testEmptyObjSize() {
        System.out.println("【空对象的字节Byte数：】");
        Object object = new Object();
        // MarkWord 64位/8字节 + class point64位/8字节(压缩后4字节) = 12字节，不是8的倍数，需要额外加上4字节的对齐填充字节，所以共16字节
        // System.out.println("【总字节：】" + ClassLayout.parseInstance(object));
        System.out.println("【空对象的总字节Byte数：】" + ClassLayout.parseInstance(object).instanceSize());
        System.out.println("【空对象的对象头的字节Byte数：】" + ClassLayout.parseInstance(object).headerSize());
        // 打印内存布局。
        // System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    /**
     * 功能描述：验证数组对象占用的内存大小。 数组对象相比较 一般的对象，会多占用32位/4个字节，这个不存在是否可压缩的选项，就是固定的4个字节。然后数组里的元素根据数据的类型占用对应的大小。
     */
    @Test
    public void testArrObjSize() {
        System.out.println("【空数组对象的字节Byte数：】");
        int[] intArr1 = new int[] {};
        // 数组对象的大小 = MarkWord 64位/8字节 + classpoint64位/8字节(压缩后4字节) + 数组占用的4字节 = 16字节，数组对象需要占用额外的四个字节，数组中的数据
        // 不开启 UseCompressedClassPointers 后，就是 MarkWord8 + classpoint64位8 + 数组占用4 + 对齐填充4 = 24字节
        // System.out.println("【总字节：】" + ClassLayout.parseInstance(new int[] {}));
        System.out.println("【总字节：】" + ClassLayout.parseInstance(intArr1).instanceSize());
        System.out.println("【对象头:】" + ClassLayout.parseInstance(intArr1).headerSize());
        System.out.println("【一个元素的数组对象的字节Byte数：】");
        int[] intArr2 = new int[] { 1 };
        System.out.println("【空数组总字节：】" + ClassLayout.parseInstance(intArr2).instanceSize());
        System.out.println("【空数组对象头:】" + ClassLayout.parseInstance(intArr2).headerSize());
        System.out.println("【两个元素的数组对象的字节Byte数：】");
        int[] intArr3 = new int[] { 1, 2 };
        System.out.println("【空数组总字节：】" + ClassLayout.parseInstance(intArr3).instanceSize());
        System.out.println("【空数组对象头:】" + ClassLayout.parseInstance(intArr3).headerSize());
        System.out.println("【三个元素的数组对象的字节Byte数：】");
        int[] intArr4 = new int[] { 1, 2, 3 };
        System.out.println("【空数组总字节：】" + ClassLayout.parseInstance(intArr4).instanceSize());
        System.out.println("【空数组对象头:】" + ClassLayout.parseInstance(intArr4).headerSize());
    }

    /**
     * 功能描述：验证包含数组的对象占用的内存大小
     */
    @Test
    public void testObjSize() {
        System.out.println("【包含数组的对象占用的内存大小：】");
        InstanceObj1 object2 = new InstanceObj1();
        // 打印对象占用内存大小，单位：字节
        // System.out.println("【总字节：】" + ClassLayout.parseInstance(object2));
        System.out.println("【包含数组的对象的总字节Byte数：】" + ClassLayout.parseInstance(object2).instanceSize());
        System.out.println("【非空对象的对象头的字节Byte数：】" + ClassLayout.parseInstance(object2).headerSize());
        // 打印内存布局。
        // System.out.println(ClassLayout.parseInstance(object2).toPrintable());
    }

    /**
     * 包含数据的对象
     */
    static class InstanceObj1 {

        int aaa = 123; // int型数据占用4个字节

        // 引用变量：存放对象地址，如String，Object、数组等。压缩后占用4个字节，64位jvm上默认使用-XX:+UseCompressedOops进行压缩，可使用-XX:-UseCompressedOops进行关闭，则在64位jvm上会占用8个字节
        int[] arr1 = {}; // 占用4个字节，不要所的话占用8个字节
        int[] arr2 = {}; // 占用4个字节，不要所的话占用8个字节
        int[] arr3 = {}; // 占用4个字节，不要所的话占用8个字节

        Map<String, String> map = new HashMap<>(); // 占用4个字节，不要所的话占用8个字节

        List<String> list = new ArrayList<>(); // 占用4个字节，不要所的话占用8个字节
    }
}
