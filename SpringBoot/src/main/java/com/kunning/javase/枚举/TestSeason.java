/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.javase.枚举;

/**
 * 功能描述：纯手写的枚举类。
 *
 * @author fengshiqing
 * @since 2019-05-13
 */
public class TestSeason {

	public static void main(String[] args) {
		Season spring = Season.SPRING;
		System.out.println(spring);
		spring.show();
		System.out.println(spring.getSeasonName());
		System.out.println(spring.getSeasonDesc());
	}

}

// 自定义季节枚举类
class Season {

	// 1、创建private final类型的常量
	private final String seasonName;
	private final String seasonDesc;

	// 2、私有化构造器。final类型的属性一旦初始化就不可修改。
	private Season(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}

	// 3、通过公共方法调用属性
	public String getSeasonName() {
		return seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

	// 4、创建枚举类的对象
	public static final Season SPRING = new Season("spring", "穿暖花开");
	public static final Season SUMMER = new Season("summer", "夏日炎炎");
	public static final Season AUTUMN = new Season("autumn", "秋高气爽");
	public static final Season WINTER = new Season("winter", "白雪皑皑");

	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
	}

	public void show() {
		System.out.println("这是一个季节");
	}

}
