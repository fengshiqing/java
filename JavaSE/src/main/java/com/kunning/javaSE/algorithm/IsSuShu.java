package com.kunning.JavaSE.algorithm;

/**
 * @author kun
 * 判断是否是素数
 */
public class IsSuShu {

	public static void main(String[] args) {

		System.out.println(isSuShu(299));
	}

	public static boolean isSuShu(double num) {
		for(int i=2; i<=Math.sqrt(num); i++) {//开平方
			if(num%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	
}
