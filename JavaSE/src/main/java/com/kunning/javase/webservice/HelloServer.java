package com.kunning.javase.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class HelloServer {

	public static void main(String[] args) {
		// JDK 内置类可以将java 应用程序发布成webservice
		// 用浏览器访问http://localhost:9999/helloService?wsdl，可以看到webservice的使用说明书。
		Endpoint.publish("http://localhost:9999/helloService", new HelloServer());
	}

	public void sayHi(String str) {
		System.out.println("hello" + str + "么么哒");
	}

}
