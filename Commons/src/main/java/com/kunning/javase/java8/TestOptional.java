package com.kunning.javase.java8;

import org.junit.Test;

import java.util.Optional;

public class TestOptional {

    @Test
    public void test() {

        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);

        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        value1 = a.orElse(1);

        //Optional.get - 获取值，值需要存在
//        System.out.println("【获取a的值】" + a.get()); // 抛出空指针
        System.out.println("【获取b的值】" + b.get());

        System.out.println(value1 + value2);
    }

}
