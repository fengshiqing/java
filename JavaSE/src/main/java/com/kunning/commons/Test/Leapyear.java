package com.kunning.commons.Test;

import java.util.Scanner;

//判断输入的年份是否是 闰年
public class Leapyear {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);//控制台输入
		System.out.println("请输入一个年份：");
		long year = 0;
		
		try{
			year = scan.nextLong();//读取控制台输入的文本
			if(year%4==0 && year%100!=0 || year%400==0) {//能被4整除但不能被100整除的，能被400整除的
				System.out.println(year + "是闰年");
			} else {
				System.out.println(year + "不是闰年");	
			}
		} catch(Exception e) {
			System.out.println("您输入的不是有效的年份！");
		}
		
	}

}
/*
java.util 包中的 Scanner 类是一个用于扫描输入文本的简单文本扫描器，可以用这个类从控制台写入数据。
该类的 nextLong() 方法可以将输入的信息扫描为一个long型的数据。
如果输入的信息不能被转为 long 型，将抛出 java.util.InputMismatchException 异常。

地球绕太阳一圈成为一年，所用时间为：365天5小时48分46秒，取365天为一年，这样4年后将多出23小时15分6秒，将近一天，
所以每4年增加一个闰日（2月29日），该年成为闰年。

*/