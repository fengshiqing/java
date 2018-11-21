package pattern;

/**
 * 代理模式。<br>
 * 这种是静态代理，就是接口和实现是一一对应的，换个被代理的类，就要新建一个对应的接口。<br>
 * 后面学了反射就可以用动态代理。<br>
 * 
 * SLF4J和Log框架的绑定就是通过静态代理实现的。<br>
 * 
 * 静态代理思想：<br>
 * 1、一个公共接口，<br>
 * 2、两个实现类，其中一个代理类，另一个是被代理类，<br>
 * 3、代理类中创建被代理类的引用，并调用被代理类的方法。
 */
public class ProxyPattern {
	public static void main(String[] args) {
		IAction iAction = new ProxyObject();
		iAction.action();
	}
}

// 一个公共接口
interface IAction {
	void action();
}

//被代理类
class ActionImpl implements IAction {
	@Override
	public void action() {
		System.out.println("被代理类开始执行");
		System.out.println("被代理类的具体逻辑。。。");
		System.out.println("被代理类结束执行");
	}
}

// 代理类
class ProxyObject implements IAction {
	IAction iActionObj;// 代理类中创建被代理类的引用，可以在声明变量时初始化，也可以新建个构造函数用于初始化。

	public ProxyObject() {// 之所以称为静态（编译期的叫静态），因为在此处必须和“被代理类”绑定在一起，强耦合。
		System.out.println("代理类创建成功");
		iActionObj = new ActionImpl();// 代理类中创建被代理类的引用
	}

	public ProxyObject(ActionImpl actionImpl) {
		System.out.println("代理类创建成功");
		iActionObj = actionImpl;// 代理类中创建被代理类的引用
	}

	@Override
	public void action() {
		System.out.println("代理类开始执行");
		iActionObj.action();// 调用被代理类的方法
		System.out.println("代理类结束执行");
	}
}
