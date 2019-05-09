package com.kunning.algorithm;

public class Fibonacci {

	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);//2的32次方
		System.out.println(Long.MAX_VALUE);//2的64次方
		
		System.out.println(Fib(40));//打印斐波那契数列的第N个数字
	}

	static int Fib(int n) {
		if(n==1 || n==2) {//斐波那契数列的第一、第二个数字都为 1.
			return 1;
		}
		
		return Fib(n-1)+Fib(n-2);
	}
}
