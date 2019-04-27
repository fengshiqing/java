package DesignPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 
 * @author win10
 * @see http://www.gulixueyuan.com/course/39/task/538/show
 */
public class Proxy_Dynamic {

	public static void main(String[] args) {
		// 1、造一个被代理的对象
		RealSubject real = new RealSubject();
		// 2、创建一个实现了InvocationHandler 接口的对象
		MyInvocationHandler handler = new MyInvocationHandler();
		// 3、调用blind方法，返回一个同样实现了real所在的类实现的接口Subject的代理类对象。
		Object obj = handler.blind(real);
		Subject sub = (Subject) obj;// 此时的sub就是代理类对象
		sub.action();// 转到对InvocationHandler 接口的实现类的invoke()方法的嗲用。
	}

}

// 和静态代理一样，需要一个接口
interface Subject {
	void action();
}

// 被代理类
class RealSubject implements Subject {
	@Override
	public void action() {
		System.out.println("我是被代理类。。。");
	}
}

// 代理类
class MyInvocationHandler implements InvocationHandler {
	private Object obj;// 实现了接口的被代理类的对象

	// 此方法两个作用：1给被代理的对象实例化，2返回“代理类”的实例对象。
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	// 静态的调用方法时，有固定的方法
	// 动态的有点不一样，当通过代理类的对象发起对被重写的方法的调用时，都会转化为对如下的方法的调用，就实现了代理。
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnVal = method.invoke(obj, args);
		return returnVal;// 这个返回值就是被代理类的方法的返回值。
	}

}
