package com.kunning.JavaSE.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Translate {

	public static void main(String[] args) {
		String str = translate("adv");
		System.out.println(str);
	}
	
	public static String translate(String str) {
		String result = "";
		try {
			byte[] s = str.getBytes("ISO-8859-1");
			System.out.println(Arrays.toString(s));
			result = new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
