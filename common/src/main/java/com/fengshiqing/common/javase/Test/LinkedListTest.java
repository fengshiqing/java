package com.fengshiqing.common.javase.Test;

import java.util.LinkedList;

public class LinkedListTest {

	public static void main(String[] args) {
		DuiLie dl = new DuiLie();
		dl.myadd("java001");
		dl.myadd("java002");
		dl.myadd("java003");
		dl.myadd("java004");
		System.out.println(dl);
		
		while(!dl.isNull()) {
			System.out.println(dl.myGet());
		}
	}
}

class DuiLie {//封装链表类LinkedList，实现先进先出。
	private LinkedList<Object> link;
	DuiLie() {
		link = new LinkedList<>();
	}
	
	void myadd(Object obj) {
		link.addFirst(obj);
	}
	
	Object myGet() {
		return link.removeLast();//先进先出，如果要实现先进后出，就用 removeFirst()
	}
	
	boolean isNull() {
		return link.isEmpty();
	}
}