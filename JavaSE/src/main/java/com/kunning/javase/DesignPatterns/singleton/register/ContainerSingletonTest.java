/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.DesignPatterns.singleton.register;

import org.junit.jupiter.api.Test;

/**
 * 功能描述：容器单例/注册式单例。
 *
 * @author fengshiqing
 * @since 2021-05-04
 */
public class ContainerSingletonTest {

    @Test
    public void testContainerSingleton() {
        Object instance1 = ContainerSingleton.getInstance("com.kunning.javase.DesignPatterns.singleton.register.Pojo");
        Object instance2 = ContainerSingleton.getInstance("com.kunning.javase.DesignPatterns.singleton.register.Pojo");
        System.out.println(instance1 == instance2);
    }
}

class Pojo {

}
