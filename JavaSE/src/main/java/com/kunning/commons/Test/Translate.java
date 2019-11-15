package com.kunning.commons.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Translate {

	public static void main(String[] args) {
		String str = translate("冯仕清");
		System.out.println(str);
	}
	
	public static String translate(String str) {
		String result = "";
		try {
			byte[] s = str.getBytes();
			System.out.println(Arrays.toString(s));
			result = new String(null, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
