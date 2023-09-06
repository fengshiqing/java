/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.collection;

import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.StringTokenizer;

//Enumeration 接口是 Iterator 迭代器的 “古老版本”
public class EnumerationTest {

	@Test
	public void test() {
		Enumeration<Object> enu = new StringTokenizer("a_b-c*-d--f-gh---ij k/@#$%^lmn", "-");
		while(enu.hasMoreElements()){
			System.out.println(enu.nextElement());
		}
	}
}	
