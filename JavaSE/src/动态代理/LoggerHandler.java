package 动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggerHandler {

	private IHelloWorld target;// 要代理的对象

	/**
	 * 构造函数
	 * @param target
	 */
	public LoggerHandler(IHelloWorld target) {
		this.target = target;
	}

	@SuppressWarnings("rawtypes")
	public IHelloWorld getLoggerProxy() {

		IHelloWorld proxy = null;
		ClassLoader loader = target.getClass().getClassLoader();// 获取代理对象的类加载器
		Class[] interfaces = new Class[] { IHelloWorld.class };

		// 当调用代理对象中的方法时，执行此代码
		InvocationHandler h = new InvocationHandler() {
			/**
			 * proxy：正在返回的那个代理对象，一般情况下，在invoke方法中不能使用该对象，会造成死循环 method：正在被调用的方法
			 * args：调用方法时，传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// proxy.toString();// 造成内存溢出：java.lang.StackOverflowError

				String methodName = method.getName();
				System.out.println(methodName + "开始执行");
				Object result = method.invoke(target, args);
				System.out.println(methodName + "【结束执行】【执行成功】【相应参数：" + result + "】");
				return result;
			}
		};

		proxy = (IHelloWorld) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}

}
