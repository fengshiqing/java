package com.array;

/**
 * @author kun
 * 选择排序：直接选择排序
 * 选择排序(Selection Sort)的基本思想是：每一次外循环选出最值放在开头。
      常用的选择排序方法有简单选择排序和堆排序。
 */
public class SelectionSort {

	public static void main(String[] args) {

		int[] num = {9,8,7,6,5,4,3,2,1,0};
		selectionSort(num);
		for(int i: num) {
			System.out.print(i+" ");
		}
	}
	
	/**
	 * 选择排序
	 * @param num
	 */
	public static void selectionSort(int[] num) { // 
		int temp = 0;// 定义一个临时变量
		for(int i=0;  i<num.length-1;  i++) { // 两层循环
			for(int j=i+1;  j<num.length;  j++) {// 每次内循环结束，最值出现在头角标位置。
				if(num[i] > num[j]) {// 从小到大
					temp = num[i];
					num[i] = num[j];// 这种每次比较都要移动，时间复杂度是：i*(j-1)
					num[j] = temp;
				}
			}
		}
	}
	
	public static void selection_sort(int[] arr) { // 这才是真正的选择排序，
		int temp, len = arr.length;
		
		for(int x = 0;  x<len-1;  x++) {
			int min = x;
			for(int y=x+1;  y<len;  y++) { // 内层循环负责找出最小值
				if(arr[min] > arr[y])
					min = y;// 取较小元素的索引，不移动元素，提高性能
			}
			/*
			 * 是不是要加个判断，if(min != i)，只有不等时，才交换位置，这样每次都要判断，而不用每次都要移动，
			 * 这样可以减少几次移动，可是增加了判断，时间复杂度到底是增加了还是减小了？
			 */
			temp = arr[min]; arr[min] = arr[x]; arr[x] = temp;// 移动位置
		}
	}
	
	
}
