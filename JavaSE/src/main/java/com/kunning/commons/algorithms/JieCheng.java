package com.kunning.commons.algorithms;

/**
 * @author kun
 * 求阶乘
 */
public class JieCheng {

	public static void main(String[] args) {

		System.out.println(jieCheng(3));
		System.out.println(jieCheng2(3));
	}

	public static int jieCheng(int x) {// 迭代的方法
		if(x==0 || x==1) {
			return 1;
		}
		return x*jieCheng(x-1);
	}
	public static int jieCheng2(int x) {// 循环求解
		if(x==0 || x==1) {
			return 1;
		}
		int sum = 1;
		for(int i=1; i<=x; i++) {
			sum = sum*i;
		}
		return sum;
	}
	
}
