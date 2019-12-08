package com.kunning.commons.Test;

import java.util.List;

public class abc {
	public static void main(String[] args) 
	{
		int i = 4;
		System.out.println("移位前 i = " + i);
		System.out.println("移位前二进制 i 的二进制表示= " + Integer.toBinaryString(i));
		System.out.println("移位前二进制 i 的二进制表示= " + Integer.toBinaryString(1));//二进制表示
		System.out.println();
	}
	
	public static int method1(List<String> list) {
	    System.out.println("List String");  
	    return 1;  
	}
	  
	public static boolean method(List<Integer> list) {  
	    System.out.println("List Int");  
	    return true;  
	}
	
}
