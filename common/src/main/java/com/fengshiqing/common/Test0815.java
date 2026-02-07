/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Test0815 {

    /**
     * 功能描述：获取泛型的类型。
     * 说明：
     * 由于泛型擦除的原因，我们是无法直接通过自身的类信息获取到泛型类型的。
     * 不过如果我们继承参数泛型对象时指定参数类型时，即可以通过父类信息获取到泛型类型。
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        Type genericSuperclass = B.class.getGenericSuperclass(); // 区别于 getSuperClass，getGenericSuperClass方法会保留泛型类型。

        ParameterizedType parameterizedType =  (ParameterizedType) genericSuperclass; // 泛型的数据类型实际为 ParameterizedType

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments(); // 获取所有泛型参数，因为泛型可能为多个，因此返回的是数组
        // 打印泛型类型
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument.getTypeName());
        }
    }

    @Getter
    @Setter
    static class A<T> {
        public T data;
    }

    static class B extends A<String>{
    }

}
