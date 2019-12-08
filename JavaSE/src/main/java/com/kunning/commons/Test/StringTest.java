/**
 * 
 */
package com.kunning.commons.Test;

/**
 * @author kun
 *
 */
public class StringTest 
{
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		String s1 = "a";
		String s2 = "bc";
		String s3 = "abcabc";
//		
//		String a1 = new String("abc");
//		String a2 = new String("abc");		
//		String b1 = "abc";
//		String b2 = "abc";
//		
//		System.out.println(a1==a2);//false，new出来的每个对象的引用都不同
//		System.out.println(a1=="abc");//false
//		System.out.println(b1==b2);//true，字符串常量池
//		System.out.println(a1.equals(b1));//true
//		System.out.println();
//		
//		System.out.println("a"==a1.substring(0, 1));//false，这个不相等，
//		System.out.println("a"==b1.substring(0, 1));//false，这个不相等，
//		System.out.println("abc"=="a".concat("bc"));//false，这个不相等，
//		System.out.println("abc"=="a".concat(s2));//false，这个不相等，
//		System.out.println("abc"==s1.concat("bc"));//false，这个不相等，
//		System.out.println("abc"==s1.concat(s2));//false，这个不相等，
//		System.out.println();
//		
//		String c1 = "a" + "bc";//字面量串联而成的字符串受“字符串池”控制
//		String c2 = s1 + s2;//变量串联而成的字符串受“堆”控制
//		System.out.println(c1==a1);//false
//		System.out.println(c1==b1);//true
//		System.out.println(c2==a1);//false
//		System.out.println(c2==b1);//false
//		System.out.println();
//		
//		System.out.println(s3==a1+a2);//false，操作变量名而得到的字符串都存在堆中，不管这个变量是引用堆还是池。
//		System.out.println(s3==b1+b2);//false
//		System.out.println(s3=="abc"+"abc");//true，直接操纵字面量，得到的对象就在池中
//		System.out.println();
//		
//		System.out.println(a1.hashCode());//只要字面量的值相等，hashcode就相等。
//		System.out.println(a2.hashCode());
//		System.out.println(b1.hashCode());
//		System.out.println(b2.hashCode());
//		System.out.println("abc".hashCode());
//		System.out.println();
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(s1+",");
		buffer.append(s2);
		System.out.println(buffer.toString());
		
	}
	
}
