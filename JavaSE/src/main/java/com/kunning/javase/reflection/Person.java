package com.kunning.javase.reflection;

import com.kunning.javase.reflection.annotation.DoSomething;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@DoSomething(key = "key111", cacheName = "cacheName111")
@Getter
@Setter
@ToString
public class Person {

	public String name;

	int age;

	private String gender;

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	private Person(String name, String name2) {
		super();
		this.name = name;
	}

	public void show() {
		System.out.println("我是一个人！");
	}

	public void display(String nation){
		System.out.println("我的国籍是：" + nation);
	}

	private void sayHello(){
		System.out.println("Hello World");
	}

}
