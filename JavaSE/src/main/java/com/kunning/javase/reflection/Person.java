package com.kunning.javase.reflection;

import com.kunning.javase.reflection.annotation.DoSomething;

@DoSomething(key = "key111", cacheName = "cacheName111")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
