package 动态代理;

public class Test {
    public static void main(String[] args) {
        // 动态代理方式调用
        IHelloWorld target = new HelloWorld();
        IHelloWorld proxy = new LoggerHandler(target).getLoggerProxy();
        int number2 = proxy.sayHello();
        System.out.println(number2);
    }
}
