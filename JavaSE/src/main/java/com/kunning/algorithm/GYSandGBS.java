package com.kunning.algorithm;

/**
 * @author kun
 * 求最大公约数GCD、最小公倍数
 */
public class GYSandGBS {

	public static void main(String[] args) {

		System.out.println("最大公约数：" + GCD(17,12));
		System.out.println("最大公约数：" + LCM(17,12));
	}
	
	public static int GCD(int a, int b) {// 最大公约数
		return a % b == 0 ? b : GCD(b, a % b);// 递归，不错（辗转相除法）
	}
	
	public static int LCM(int a, int b) {// 最小公倍数
		return a * b / GCD(a, b);
	}
	
}
