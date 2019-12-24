package com.kunning.web.springIoC.beans;

public class HelloWorld {

	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMessage() {
		System.out.println("【Spring容器中的对象：】" + message);
	}

}
