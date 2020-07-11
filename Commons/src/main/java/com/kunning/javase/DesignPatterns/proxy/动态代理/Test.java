package com.kunning.javase.DesignPatterns.proxy.动态代理;

/**
 * 功能描述：代理
 *
 * @author fengshiqing
 * @since 2020/04/19
 */
public class Test {
    public static void main(String[] args) {
        // 动态代理方式调用
        IHelloWorld target = new HelloWorld();
        IHelloWorld proxy = new LoggerHandler(target).getLoggerProxy();
        int number2 = proxy.sayHello();
        System.out.println(number2);
    }
}
