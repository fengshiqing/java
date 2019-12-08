package com.kunning.commons.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//判断string字符串中是否存在汉字
public class IsHaveHanZi {

	public static void main(String[] args) {
		if(isHaveHanZi("我是冯仕清")) {
			System.out.println("有汉字");
		} else {
			System.out.println("没汉字");
		}
	}
	
	public static boolean isHaveHanZi(String str){
		boolean flag = true;
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			for (int i = 0; i<=matcher.groupCount(); i++) {
				count = count + 1;
			}
		}
		if(count==0) {
			flag = false;
		}
		return flag;
	}

}