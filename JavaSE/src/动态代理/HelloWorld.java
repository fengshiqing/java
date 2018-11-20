package 动态代理;

public class HelloWorld implements IHelloWorld {

	@Override
	public int sayHello() {
		System.out.println("HelloWorld");
		return 1 + 1;
	}

	public static void main(String[] args) {
		// 普通方式
		HelloWorld hello = new HelloWorld();
		int number1 = hello.sayHello();
		System.out.println(number1);
		System.out.println();

		// 代理方式
		IHelloWorld target = new HelloWorld();
		IHelloWorld proxy = new LoggerHandler(target).getLoggerProxy();
		int number2 = proxy.sayHello();
		System.out.println(number2);
	}

}
