/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述：JDK动态代理。
 *
 * @author 冯仕清
 * @since 2019-05-01
 */
// http://www.gulixueyuan.com/course/39/task/538/show
public class ProxyDynamicTest {

    // 步骤一：和静态代理一样，需要一个接口
    interface IHelloWorld {
        void action();

        int sayHello();
    }

    // 步骤二：目标类、实现类、被代理类
    static class HelloWorld implements IHelloWorld {
        @Override
        public void action() {
            System.out.println("我是目标类/被代理类，最终执行的代码是我。");
        }

        @Override
        public int sayHello() {
            System.out.println("嗨，JDK动态代理，你好呀...");
            return 1 + 1;
        }
    }

    // 步骤三：代理类，必须实现 InvocationHandler 接口
    static class LoggerHandler implements InvocationHandler {
        private final Object target;// 实现了接口的被代理类的对象

        /**
         * 初始化时绑定目标类
         */
        public LoggerHandler(IHelloWorld target) {
            this.target = target;
        }

        /**
         * @param proxy  代理对象，一般情况下，在invoke方法中不能使用该对象，会造成死循环
         * @param method 正在被调用的方法
         * @param args   调用方法时，传入的参数
         *
         * @return 方法返回值
         *
         * @throws Exception 抛出异常
         */
        // 静态代理和动态代理的调用方法不一样，通过代理类的对象发起对被重写的方法的调用时，都会转化为对如下的方法的调用，就实现了代理。
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            // proxy.toString();// 造成内存溢出：java.lang.StackOverflowError
            String methodName = method.getName(); // 获取方法名称
            System.out.println(methodName + "预处理操作——————开始日志");
            Object returnVal = method.invoke(target, args);
            System.out.println(methodName + "调用后处理——————结束日志");
            return returnVal;// 这个返回值就是被代理类的方法的返回值。
        }
    }

    @Test
    public void test() {
        // 1、造一个被代理的对象
        IHelloWorld target = new HelloWorld();
        // 2、创建一个实现了InvocationHandler 接口的对象
        LoggerHandler myproxy = new LoggerHandler(target);
        // 3、获取一个同样实现了target的的接口的代理类对象。
        IHelloWorld proxy = (IHelloWorld) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), myproxy);
        proxy.action();// 转到对InvocationHandler 接口的实现类的invoke()方法的嗲用。
        proxy.sayHello();
    }
}

// JDK动态代理的代理对象在创建时，需要使用业务实现类所实现的接口作为参数（因为在后面代理方法时需要根据接口内的方法名进行调用）。
// 如果业务实现类是没有实现接口而是直接定义业务方法的话，就无法使用JDK动态代理了。
// 并且，如果业务实现类中新增了接口中没有的方法，这些方法是无法被代理的（因为无法被调用）。


// 静态代理是通过在代码中显式定义一个业务实现类一个代理，在代理类中对同名的业务方法进行包装，用户通过代理类调用被包装过的业务方法；
// JDK动态代理是通过接口中的方法名，在动态生成的代理类中调用业务实现类的同名方法；
// CGlib动态代理是通过继承业务类，生成的动态代理类是业务类的子类，通过重写业务方法进行代理；
