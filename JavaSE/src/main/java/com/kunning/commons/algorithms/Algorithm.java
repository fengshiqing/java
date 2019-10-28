/*
 * Copyright (C), 2002-2018, XXXXX公司
 * FileName: Algorithm.java
 * Author:   kun
 * DateTime: 2018年5月5日 下午7:17:08
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.kunning.commons.algorithms;

/**
 * 功能描述：整理常用的算法，具体看此类的单元测试类：AlgorithmTest.java
 *
 * @author 冯仕清
 */
public class Algorithm {

    // =======================================================素数=======================================================

    /**
     * 功能描述：列出 {@code number} 以内的素数
     *
     * @param number 求 {@code number} 以内的素数
     */
    public static void getPrimeNum(int number) {

        int count = 0;
        for (int i = 1; i < number; i++) {
            if (isPrimeNum(i)) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println("【素数的个数是】: " + count);
    }

    /**
     * 功能描述：判断是否为素数。true素数，false非素数
     *
     * @param number 整数数字
     */
    public static boolean isPrimeNum(int number) {
        if (number < 2) { // 小于2的数，都不是素数
            return false;
        }
        if (number == 2) { // 2是素数
            return true;
        }
        if (number % 2 == 0) { // 大于2的偶数都不是素数
            return false;
        }

        for (int i = 3; i <= Math.sqrt(number); i++) { // 开平方
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // =====================================================斐波那契数列=====================================================

    /**
     * 功能描述：打印第 {@code n} 个斐波那契数列
     *
     * @param n 打印第 {@code n} 个斐波那契数列
     *
     * @return 第 {@code n} 个斐波那契数字
     */
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) { // 斐波那契数列的第一、第二个数字都为 1.
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // =======================================================阶乘=======================================================

    public static int factorial(int x) {// 迭代的方法
        if(x==0 || x==1) {
            return 1;
        }
        return x*factorial(x-1);
    }

    public static int factorial2(int x) {// 循环求解
        if(x==0 || x==1) {
            return 1;
        }
        int sum = 1;
        for(int i=1; i<=x; i++) {
            sum = sum*i;
        }
        return sum;
    }

    // ==================================================最大公约数、最小公倍数==================================================

    /**
     * 功能描述：求最大公约数。
     * greatest common divisor(gcd)
     *
     * @param a 数字1
     * @param b 数字2
     *
     * @return 最大公约数
     */
    public static int GCD(int a, int b) {// 最大公约数
        return a % b == 0 ? b : GCD(b, a % b);// 递归，不错（辗转相除法）
    }

    /**
     * 功能描述：求最小公倍数。
     * lowest common multiple (LCM)
     *
     * @param a 数字1
     * @param b 数字2
     *
     * @return 最小公倍数
     */
    public static int LCM(int a, int b) {// 最小公倍数
        return a * b / GCD(a, b);
    }

    // ======================================================随机数======================================================

    /**
     * 功能描述：获取 从 [start, end) 之间的随机整数
     *
     * @param start 范围起点
     * @param end   范围终点
     *
     * @return 从 start 到 end 之间的随机整数
     */
    public static int getRandomNum(int start, int end) {
        double num = start + Math.random() * (end - start);
        return (int) num;
    }

    /**
     * 功能描述：n位数字组成的随机字符串。
     * 说明：可以用来做验证码。
     *
     * @param num 位数
     *
     * @return num 位数字组成的随机字符串
     */
    public static String getRandomStr(int num) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int randomNum = getRandomNum(0, 10); // 生成[0,10)区间内的整数
            strBuilder.append(randomNum);//将intValue强制转化成char类型后接到result后面
        }
        return strBuilder.toString();
    }

    // =====================================================花样多边形=====================================================

    /**
     * 功能描述: 打印多边形
     */
    public static void polygon() {

        System.out.println("左直角三角形");// 直角三角形
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("右直角三角形");// 右直角三角形
        for (int i = 9; i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int z = i; z <= 9; z++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("倒右直角三角形");
        for (int i = 9; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("倒左直角三角形");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int z = i; z <= 9; z++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("等腰三角形"); // 等腰三角形
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                System.out.print(" ");
            }
            for (int z = 0; z < 2 * i + 1; z++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("三角帆："); // 三角帆：
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }

        System.out.println("矩形："); // 矩形
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("平行四边形："); // 平行四边形
        for (int i = 9; i >= 1; i--) {
            for (int z = 1; z <= i; z++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 9; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("左直角梯形："); // 左直角梯形
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j <= 9 + i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("右直角梯形："); // 右直角梯形
        for (int i = 0; i < 9; i++) {
            for (int z = 9 - i; z > 1; z--) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 9 + i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("等腰梯形："); // 等腰梯形
        for (int i = 0; i < 9; i++) {
            for (int z = 9 - i; z > 1; z--) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 9 + 2 * i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }

    // =====================================================乘法口诀表=====================================================

    public static void multiplicationTable() {
        long startTime = System.currentTimeMillis();//开始时间
        for(int i=1,j=1; j<=9; i++) {
            System.out.print(i + "x" + j + "=" + i*j + " ");
            if(i == j) {
                System.out.println();
                i=0;//下一行将i 重置为0，因为之后会进行 ++ 运算
                j++;
            }
        }
        long endTime = System.currentTimeMillis();//结束时间
        long time = endTime-startTime;
        System.out.println("用时：" + time);
    }

    public static void multiplicationTable2() {
        for(int i=1;  i<=9;  i++) {
            for(int j=1;  j<=i;  j++) {
                System.out.print(i + "*" + j + " = " + i*j + "   ");
            }
            System.out.print("\n");
        }
    }


    // =======================================================闰年=======================================================

    public static boolean judgeLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}
