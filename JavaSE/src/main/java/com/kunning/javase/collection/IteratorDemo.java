package com.kunning.javase.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class IteratorDemo {
	// 面试题
	@Test
	public void testFor3() {
		String[] strArr = new String[] { "AA", "BB", "DD" };
		for (String s : strArr) {// 增强for循环
			s = "MM";// 每次循环从strArr取一个变量给s，没有改变原有的strArr数组的值
			System.out.println(s);
		}

		for (int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
		}
	}

	@Test
	public void testFor2() {
		String[] str = new String[] { "AA", "BB", "DD" };
		for (int i = 0; i < str.length; i++) {
			str[i] = i + "";
		}

		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
	}

	// ***********************************************
	// 增强for循环
	@Test
	public void testFor1() {
		String[] str = new String[] { "AA", "BB", "DD" };
		for (String s : str) {
			System.out.println(s);
		}
	}

	// 增强for循环
	@Test
	public void testFor() {
		Collection coll = new ArrayList();
		coll.add(123);
		coll.add(new String("AA"));
		coll.add(new Date());
		coll.add("BB");
		coll.add(new Person("MM", 23));

		for (Object i : coll) {
			System.out.println(i);
		}
	}

	// 错误的写法
	@Test
	public void test2() {
		Collection coll = new ArrayList();
		coll.add(123);
		coll.add(new String("AA"));
		coll.add(new Date());
		coll.add("BB");
		coll.add(new Person("MM", 23));

		Iterator i = coll.iterator();

		while ((i.next()) != null) {// i.next()执行了一遍
			// java.util.NoSuchElementException
			System.out.println(i.next());// i.next()又执行了一遍，跳过了一个元素
		}
	}

	// 标准正确的Iterator写法
	@Test
	public void test1() {
		Collection coll = new ArrayList();
		coll.add(123);
		coll.add(new String("AA"));
		coll.add(new Date());
		coll.add("BB");
		coll.add(new Person("MM", 23));

		Iterator i = coll.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}
}
