/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.algorithms;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 功能描述：排序算法。冒泡排序最值出现在结尾，选择排序最值出现在末尾
 *
 * @author 冯仕清
 * @since 2014/12/27
 */
public class SortAlgorithmTest {

    private static final int[] intArr = { 7, 8, 5, 6, 4, 53, 33, 49, 28, 5, 51, 54, 77, 66 };

    /**
     * JDK自带的排序
     */
    @Test
    public void jdkSort() {
        System.out.println("排序前：" + Arrays.toString(intArr));

        Arrays.sort(intArr); // java API中自带的排序函数，排序方法
        System.out.println("排序后：" + Arrays.toString(intArr));
        for (int i : intArr) {
            System.out.print(i + " ");
        }
        Arrays.parallelSort(intArr); // 并行排序，数据量越大，parallelSort的优势就越明显。
        System.out.println("排序后：" + Arrays.toString(intArr));
    }

    // ======================================================冒泡排序======================================================

    /**
     * 冒泡排序原理：比较相邻的两个元素，把最大/小的元素放在最后。----Bubble sort 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大/小的数。 针对所有的元素重复以上的步骤，除了最后一个。 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * 由于它的简洁，冒泡排序通常被用来对于程序设计入门的学生介绍算法的概念。
     */
    @Test
    public void bubbleSort() {
        int len = intArr.length;// 数组长度
        int temp;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {// 每次内循环结束，最值出现在最后
                if (intArr[j] > intArr[j + 1]) {// 从小到大，找出大的数字往后移
                    temp = intArr[j];// 交换arr[j] 和 arr[j+1]
                    intArr[j] = intArr[j + 1];
                    intArr[j + 1] = temp;
                }
            }
        }
    }

    // ======================================================选择排序======================================================

    /**
     * 功能描述：选择排序(Selection Sort)的基本思想是：每一次外循环选出最值放在开头。常用的选择排序方法有简单选择排序和堆排序。 选择排序_1
     */
    @Test
    public void selectionSort() {
        int temp;// 定义一个临时变量
        for (int i = 0; i < intArr.length - 1; i++) { // 两层循环
            for (int j = i + 1; j < intArr.length; j++) {// 每次内循环结束，最小值出现在头角标位置。
                if (intArr[i] > intArr[j]) { // 从小到大
                    temp = intArr[i];
                    intArr[i] = intArr[j];// 这种每次比较都要移动，时间复杂度是：i*(j-1)
                    intArr[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序_2
     */
    @Test
    public void selection_sort() { // 这才是真正的选择排序，
        int temp;
        for (int i = 0; i < intArr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < intArr.length; j++) { // 内层循环负责找出最小值
                if (intArr[min] > intArr[j])
                    min = j;// 取较小元素的索引，不移动元素，提高性能
            }
            /*
             * 是不是要加个判断，if(min != i)，只有不等时，才交换位置，这样每次都要判断，而不用每次都要移动， 这样可以减少几次移动，可是增加了判断，时间复杂度到底是增加了还是减小了？
             */
            temp = intArr[min];
            intArr[min] = intArr[i];
            intArr[i] = temp;// 移动位置
        }
    }

    /**
     * 功能描述：插入排序。类似打扑克牌时的抓牌排序的过程
     */
    @Test
    public void insertSort() {
        System.out.println(Arrays.toString(intArr));

        for (int i = 1; i < intArr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (intArr[j] < intArr[j - 1]) {
                    int temp = intArr[j];
                    intArr[j] = intArr[j - 1];
                    intArr[j - 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(intArr));
    }

    /**
     * 功能描述：归并排序
     */
    @Test
    public void mergeSort() {

    }

}
