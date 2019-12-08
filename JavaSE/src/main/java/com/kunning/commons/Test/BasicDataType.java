package com.kunning.commons.Test;

public class BasicDataType {

	public static void main(String[] args) {

		//float f = 3.14;
		//int i = 'a';
		//short x = 32761;
		//x = x + 1;
		Double d1 = new Double(-1.0/0.0);
		Double d2 = new Double(0.0/0.0);
		
		// returns true if this Double value is a Not-a-Number (NaN) 
		System.out.println(d1 + " = " + d1.isNaN());
		// returns false for other cases
		System.out.println(d2 + " = " + d2.isNaN());
		char ch = '\u03C0';
		System.out.println(ch);
	}

}
