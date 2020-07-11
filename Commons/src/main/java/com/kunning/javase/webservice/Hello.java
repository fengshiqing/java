package com.kunning.javase.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class Hello {
	/**
	 * Endpoint Web服务端点 使用在此类定义的静态方法创建端点。一个端点总是绑定到一个Binding 和一个实现者，这两项都是在创建端点时设置的。
	 *
	 * 端点要么处于已发布状态，要么处于未发布状态。可以使用publish方法 开始发布端点，此时端点开始接受传入请求。相反，可以使用stop方法停
	 * 止接受传入请求并取消端点。一旦停止，就不能再次发布端点。
	 *
	 * 可以在端点上设置Executor以便更好地控制用来指派传入请求的线程。例如
	 * ，通过创建ThreadPoolExecutor并向端点注册可以启动带有特定参数的线程池。
	 *
	 * 处理程序链可以使用所含的Binding来设置。
	 *
	 * 端点可以使一个数据文档(如WSDL和XMLSchema文档)列表与之绑定。发布时， JAX-WS实现将根据实现者上存在的注释，尽可能多地重用这些元数据，
	 * 而不是生成新的数据
	 *
	 * address：一个URI,指定要使用的地址和传输协议 implementor:端点实现者，表示是哪个类实现的服务。
	 */
	public String sayHi(String name) {
		System.out.println("姓名  name = " + name);
		return "欢迎访问我：hi!" + name;
	}

	public static void main(String[] args) {
		// 这里的地址可以是随便写的，表示的意思是访问这个webservice的地址是多少。//当然也可以把这个地址改成一个确定的ip地址。
		// 注意后面的链接地址是在程序中定义的address,要注意的是端口号不能和本机程序中的端口号重名。
		Endpoint.publish("http://localhost:8888/one", new Hello());
	}

}
