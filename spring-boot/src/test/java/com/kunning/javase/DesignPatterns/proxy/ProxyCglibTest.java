/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.proxy;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 功能描述：CGlib动态代理。
 *
 * @author 冯仕清
 * @since 2019-05-01
 */
// https://www.cnblogs.com/writeLessDoMore/p/6973853.html
public class ProxyCglibTest {

    // 1：定义业务类，无需实现接口（当然，实现接口也可以，不影响的）
    static class HelloWorld {
        public void sayHello() {
            System.out.println("嗨，CGlib代理，你好呀...");
        }
    }

    // 2：实现 MethodInterceptor方法代理接口，创建代理类
    static class HelloCglib implements MethodInterceptor {

        // 相当于JDK动态代理中的绑定
        public Object getInstance(Object target) {
            Enhancer enhancer = new Enhancer(); // 创建加强器，用来创建动态代理类
            enhancer.setSuperclass(target.getClass()); // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
            // 设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
            enhancer.setCallback(this);
            return enhancer.create(); // 创建动态代理类对象，并返回
        }

        // 实现回调方法
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            String methodName = method.getName(); // 获取方法名称
            System.out.println(methodName + "预处理——————开始日志");
            proxy.invokeSuper(obj, args); // 调用业务类（父类中）的方法
            System.out.println(methodName + "调用后操作——————结束日志");
            return null;
        }
    }

    // 3：创建业务类和代理类对象，然后通过 代理类对象.getInstance(业务类对象) 返回一个动态代理类对象（它是业务类的子类，可以用业务类引用指向它）。最后通过动态代理类对象进行方法调用。
    @Test
    public void test() {
        HelloCglib helloCglib = new HelloCglib();
        HelloWorld helloWorld = (HelloWorld) helloCglib.getInstance(new HelloWorld()); // 传入一个类，生成这个类的代理类
        helloWorld.sayHello();
    }

}
