package com.kunning.JavaSE.string;

public class StringDemo {

	public static void main(String[] args) {
		byte[] byteArr = new byte[2];
		//String str = new String(byteArr, 0, 3);// java.lang.StringIndexOutOfBoundsException: String index out of range: 3
		String str = new String(byteArr, 0, 2);
		System.out.println(str);
	}

}
