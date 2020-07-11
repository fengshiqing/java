package com.kunning.javase.algorithms;

import java.util.Arrays;

/**
 * 功能描述：排序算法。冒泡排序最值出现在结尾，选择排序最值出现在末尾
 *
 * @author 冯仕清
 * @since 2014/12/27
 */
public class SortAlgorithm {

    /**
     * 主函数
     */
    public static void main(String[] args) {

        int[] arr = {22, 33, 11, 56, 5};
        System.out.println("排序前：" + Arrays.toString(arr));

        Arrays.sort(arr); // java API中自带的排序函数，排序方法
        System.out.println("排序后：" + Arrays.toString(arr));
        for (int i : arr) {
            System.out.print(i + " ");
        }
        Arrays.parallelSort(arr); // 并行排序，数据量越大，parallelSort的优势就越明显。
        System.out.println("排序后：" + Arrays.toString(arr));

        // ================================================================================

        int[] number = {9, 3, 1, 5, 7};
        bubbleSort(number);
        for (int i : number)
            System.out.print(i + " ");


        // 测试排序算法
        int[] num = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        selectionSort(num);
        for (int i : num) {
            System.out.print(i + " ");
        }
    }

    // ======================================================冒泡排序======================================================

    /**
     * 冒泡排序原理：比较相邻的两个元素，把最大/小的元素放在最后。----Bubble sort
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大/小的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * 由于它的简洁，冒泡排序通常被用来对于程序设计入门的学生介绍算法的概念。
     */
    public static void bubbleSort(int[] arr) {
        int len = arr.length;// 数组长度
        int temp;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {// 每次内循环结束，最值出现在最后
                if (arr[j] > arr[j + 1]) {// 从小到大，找出大的数字往后移
//					if (arr[j] < arr[j + 1]) {// 从大到小，找出小的数字往后移
                    temp = arr[j];// 交换arr[j] 和 arr[j+1]
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // ======================================================选择排序======================================================

    /**
     * 功能描述：选择排序(Selection Sort)的基本思想是：每一次外循环选出最值放在开头。常用的选择排序方法有简单选择排序和堆排序。
     * 选择排序_1
     */
    public static void selectionSort(int[] arr) { //
        int temp = 0;// 定义一个临时变量
        for (int i = 0; i < arr.length - 1; i++) { // 两层循环
            for (int j = i + 1; j < arr.length; j++) {// 每次内循环结束，最值出现在头角标位置。
                if (arr[i] > arr[j]) {// 从小到大
                    temp = arr[i];
                    arr[i] = arr[j];// 这种每次比较都要移动，时间复杂度是：i*(j-1)
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序_2
     */
    public static void selection_sort(int[] arr) { // 这才是真正的选择排序，
        int temp, len = arr.length;

        for (int x = 0; x < len - 1; x++) {
            int min = x;
            for (int y = x + 1; y < len; y++) { // 内层循环负责找出最小值
                if (arr[min] > arr[y])
                    min = y;// 取较小元素的索引，不移动元素，提高性能
            }
            /*
             * 是不是要加个判断，if(min != i)，只有不等时，才交换位置，这样每次都要判断，而不用每次都要移动，
             * 这样可以减少几次移动，可是增加了判断，时间复杂度到底是增加了还是减小了？
             */
            temp = arr[min];
            arr[min] = arr[x];
            arr[x] = temp;// 移动位置
        }
    }

}
