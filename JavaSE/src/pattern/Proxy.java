package pattern;

/**
 * 代理模式。<br>
 * 这种是静态代理，就是接口和实现是一一对应的，换个被代理的类，就要新建一个对应的接口。<br>
 * 后面学反射了可以用动态代理。<br>
 * 
 * 静态代理思想：一个公共接口，两个实现类，其中一个代理类，另一个是被代理类，代理类中创建被代理类的引用，并调用被代理类的方法。
 */
public class Proxy {

	public static void main(String[] args) {
		IAction iAction = new ProxyObject();
		iAction.action();
	}

}

// 接口
interface IAction {
	void action();
}

// 代理类
class ProxyObject implements IAction {
	IAction iActionObj;

	public ProxyObject() {
		System.out.println("代理类创建成功");
		iActionObj = new ActionImpl();
	}

	@Override
	public void action() {
		System.out.println("代理类开始执行");
		iActionObj.action();
		System.out.println("代理类结束执行");
	}
}

// 被代理类
class ActionImpl implements IAction {
	@Override
	public void action() {
		System.out.println("被代理类开始执行");
		System.out.println("具体逻辑。。。");
		System.out.println("被代理类结束执行");
	}
}
