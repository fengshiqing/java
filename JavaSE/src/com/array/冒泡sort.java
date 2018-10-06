package com.array;

import java.util.Scanner;

//冒泡排序最值出现在结尾，选择排序最值出现在末尾
/**
 * @author kun
 * 冒泡排序：比较相邻的两个元素，把最大/小的元素放在最后。----Bubble sort
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大/小的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * 
 * 由于它的简洁，冒泡排序通常被用来对于程序设计入门的学生介绍算法的概念。
 */
public class 冒泡sort {

	public static void main(String[] args) {
		//Scanner scanner = new Scanner(System.in);
		//String in = scanner.nextLine();
		int[] number = {9,3,1,5,7};
		bubble_sort(number);
		for(int i :number) 
			System.out.print(i + " ");
	}
	
	public static void bubble_sort(int[] arr) {//
		int i, j, temp, len = arr.length;
		
		for(i=0;  i<len-1;  i++)
			for(j=0;  j<len-1-i;  j++)// 每次内循环结束，最值出现在最后
				if (arr[j] > arr[j+1])// 从小到大
					{ temp = arr[j]; arr[j] = arr[j+1]; arr[j+1] = temp; }//交换arr[j] 和 arr[j+1]
	}
	
	public static void swap(Integer[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
