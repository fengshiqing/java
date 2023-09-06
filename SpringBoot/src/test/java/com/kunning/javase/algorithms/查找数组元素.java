/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.algorithms;

import java.util.Arrays;

public class 查找数组元素 {

    public static void main(String[] args) {
        int[] number = {1, 3, 5, 7, 9, 11, 13};
        //int index = getIndex(number, 5);
        //System.out.println("index=" + index);
        int[] numArr = new int[10];
        System.arraycopy(number, 0, numArr, 0, 7);
        System.out.println(Arrays.toString(numArr));

        System.out.println(halfSearch(number, 10));
    }

    // 普通查找方式，从头开始按顺序查找
    public static int getIndex(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    //折半查找
    public static int halfSearch(int[] arr, int key) {
        int min = 0, max = arr.length - 1, mid = (min + max) / 2;

        while (arr[mid] != key) {
            if (key > arr[mid])
                min = mid + 1;
            else
                max = mid - 1;
            if (min > max) {
                return -1;
            }
            mid = (min + max) / 2;
        }
        return mid;
    }

    // 折半的第二种方式。
    public static int halfSearch_2(int[] arr, int key) {
        int min = 0, max = arr.length - 1, mid;

        while (min <= max) {
            mid = (max + min) >> 1;

            if (key > arr[mid])
                min = mid + 1;
            else if (key < arr[mid])
                max = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
