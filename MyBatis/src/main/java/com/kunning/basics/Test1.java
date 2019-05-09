package com.kunning.basics;

public class Test1 {

	public static void m() {
		System.out.println("m()");
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Test1 t = null;
		t.m();// m()方法是static的，应该用  类名.方法名  调用，这样调用也行。
	}

}
