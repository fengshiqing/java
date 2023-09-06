/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.algorithms;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：整理常用的算法，具体看此类的单元测试类：AlgorithmTest.java
 *
 * @author 冯仕清
 * @since 2014-07-27
 */
public class AlgorithmTest {

    // =======================================================素数=======================================================

    /**
     * 功能描述：列出 指定数字 以内的素数
     *
     * @param number 指定数字
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

    @Test
    public void getPrimeNum() {
        AlgorithmTest.getPrimeNum(100);
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

    @Test
    public void isPrimeNum() {
        boolean bool = AlgorithmTest.isPrimeNum(100);
        System.out.println("【是素数吗：】" + bool);
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

    @Test
    public void fibonacci() {
        int fibonacciNum = AlgorithmTest.fibonacci(10);
        System.out.println("【斐波那契数字是：】" + fibonacciNum);
    }

    // =======================================================阶乘=======================================================

    public static int factorial(int x) {// 迭代的方法
        if (x == 0 || x == 1) {
            return 1;
        }
        return x * factorial(x - 1);
    }

    public static int factorial2(int x) {// 循环求解
        if (x == 0 || x == 1) {
            return 1;
        }
        int sum = 1;
        for (int i = 1; i <= x; i++) {
            sum = sum * i;
        }
        return sum;
    }

    @Test
    public void factorial() {
        System.out.println("【阶乘结果：】" + AlgorithmTest.factorial(10));
        System.out.println("【阶乘结果：】" + AlgorithmTest.factorial2(10));
    }


    // ==================================================最大公约数、最小公倍数==================================================

    /**
     * 功能描述：求最大公约数。 greatest common divisor(gcd)
     *
     * @param a 数字1
     * @param b 数字2
     *
     * @return 最大公约数
     */
    public static int GCD(int a, int b) {// 最大公约数
        return a % b == 0 ? b : GCD(b, a % b);// 递归，不错（辗转相除法）
    }

    @Test
    public void GCD() {// 最大公约数
        int gcdNum = AlgorithmTest.GCD(15, 6);
        System.out.println("【最大公约数：】" + gcdNum);
    }

    /**
     * 功能描述：求最小公倍数。 lowest common multiple (LCM)
     *
     * @param a 数字1
     * @param b 数字2
     *
     * @return 最小公倍数
     */
    public static int LCM(int a, int b) {// 最小公倍数
        return a * b / GCD(a, b);
    }

    @Test
    public void LCM() {// 最小公倍数
        int lcmNum = AlgorithmTest.LCM(15, 6);
        System.out.println("【最小公倍数：】" + lcmNum);
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

    @Test
    public void getRandomNum() {
        int fibonacciNum = AlgorithmTest.getRandomNum(1, 10);
        System.out.println("【随机数：】" + fibonacciNum);
    }

    /**
     * 功能描述：n位数字组成的随机字符串。 说明：可以用来做验证码。
     *
     * @param num 位数
     *
     * @return num 位数字组成的随机字符串
     */
    public static String getRandomStr(int num) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int randomNum = getRandomNum(0, 10); // 生成[0,10)区间内的整数
            strBuilder.append(randomNum);// 将intValue强制转化成char类型后接到result后面
        }
        return strBuilder.toString();
    }

    @Test
    public void getRandomStr() {
        String randomStr = AlgorithmTest.getRandomStr(6);
        System.out.println("【随机数字字符串：】" + randomStr);
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

    @Test
    public void testPolygon() {
        AlgorithmTest.polygon();
    }

    // =====================================================乘法口诀表=====================================================

    public static void multiplicationTable() {
        long startTime = System.currentTimeMillis();// 开始时间
        for (int i = 1, j = 1; j <= 9; i++) {
            System.out.print(i + "x" + j + "=" + i * j + " ");
            if (i == j) {
                System.out.println();
                i = 0;// 下一行将i 重置为0，因为之后会进行 ++ 运算
                j++;
            }
        }
        long endTime = System.currentTimeMillis();// 结束时间
        long time = endTime - startTime;
        System.out.println("用时：" + time);
    }

    public static void multiplicationTable2() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + " = " + i * j + "   ");
            }
            System.out.print("\n");
        }
    }

    @Test
    public void testMultiplicationTable() {
        AlgorithmTest.multiplicationTable();
        AlgorithmTest.multiplicationTable2();
    }

    // =======================================================闰年=======================================================

    public static boolean judgeLeapYear(int year) {
        // 地球绕太阳一圈成为一年，所用时间为：365天5小时48分46秒，取365天为一年，这样4年后将多出23小时15分6秒，将近一天，
        // 所以每4年增加一个闰日（2月29日），该年成为闰年。
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    @Test
    public void judgeLeapYear() {
        boolean bool = AlgorithmTest.judgeLeapYear(1900);
        System.out.println("是否为闰年：】" + bool);
    }

    @Test
    public void isHaveHanZi() {
        System.out.println("【是否有汉字：】" + AlgorithmTest.isHaveHanZi("我是冯仕清"));
        System.out.println("【是否有汉字：】" + AlgorithmTest.isHaveHanZi("abcdefg123"));
    }

    // ======================================================匹配汉字======================================================

    public static boolean isHaveHanZi(String str) {
        int count = 0;
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count != 0;
    }

}
