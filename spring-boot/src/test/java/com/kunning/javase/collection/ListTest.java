/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.collection;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
	@Test
	public void test() {
		String[] strArr = { "a", "b", "c", "d", "e" };
		ArrayList<String> arrayList_1 = new ArrayList<String>(Arrays.asList(strArr));// 直接用 ArrayList 类型接收
		List<String> arrayList_2 = Arrays.asList(strArr);// 多态变量接收，两者一样
		System.out.println(arrayList_1);// [a, b, c, d, e]
		System.out.println(arrayList_2);// [a, b, c, d, e]
		
		System.out.println(arrayList_1.containsAll(arrayList_2));// true
		System.out.println(arrayList_2.containsAll(arrayList_1));// true
		
		System.out.println(Arrays.toString(arrayList_1.toArray()));// 等价于 System.out.println(arrayList_1);
		System.out.println(arrayList_1.toArray().toString());
		System.out.println(arrayList_2.toArray());
		System.out.println(arrayList_1);
		System.out.println(new Object());
		System.out.println(new ListTest());
	}
	
	//ArrayList：List的主要实现类
	/*
	 * List中相对于Collection，新增加的方法
	 *  void add(int index, Object ele):在指定的索引位置index添加元素ele
	*	boolean addAll(int index, Collection eles)
	*	Object get(int index):获取指定索引的元素
	*	Object remove(int index):删除指定索引位置的元素
	*	Object set(int index, Object ele):设置指定索引位置的元素为ele
	*	int indexOf(Object obj):返回obj在集合中首次出现的位置。没有的话，返回-1
	*	int lastIndexOf(Object obj)：返回obj在集合中最后一次出现的位置.没有的话，返回-1
	*	List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex结束的左闭右开一个子list
	*	
	*	List常用的方法：增(add(Object obj)) 删(remove) 改(set(int index,Object obj))
	*				查(get(int index)) 插(add(int index, Object ele)) 长度(size())
	 */
	@Test
	public void testList2(){
		List list = new ArrayList();
		list.add(123);
		list.add(456);
		list.add(new String("AA"));
		list.add(new String("GG"));
		list.add(456);
		list.add(null);// 可以放多个null
		list.add(null);
		System.out.println(list.indexOf(456));
		System.out.println(list.lastIndexOf(456));
		System.out.println(list.indexOf(123) == list.lastIndexOf(123));
		System.out.println(list.indexOf(444));
		
		List list1 = list.subList(0, 3);
		System.out.println(list1);
	}

	@Test
	public void testList1(){
		List list = new ArrayList();
		list.add(123);
		list.add(456);
		list.add(new String("AA"));
		list.add(new String("GG"));
		System.out.println(list);
		list.add(0,555);
		System.out.println(list);
		Object obj = list.get(1);
		System.out.println(obj);
		list.remove(0);
		System.out.println(list.get(0));
		list.set(0, 111);
		System.out.println(list.get(0));
	}
}
