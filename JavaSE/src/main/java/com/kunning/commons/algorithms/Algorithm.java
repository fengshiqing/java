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
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author kun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Algorithm {

    // 整理常用的算法，具体看此类的单元测试类：AlgorithmTest.java

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
        if (n == 1 || n == 2) {//斐波那契数列的第一、第二个数字都为 1.
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
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
    public static void main(String[] args) {
        // 产生一个[0，1)之间的随机数,大于等于 0.0 且小于 1.0 的[0,1)范围内的 double 类型的数
        System.out.println("[0,1)范围内的随机数：" + Math.random());//Random类也可以生成随机数，具体的看API文档
        System.out.println("[1,10)范围内的随机数：" + (1 + Math.random() * 9));//从1到10的 double 型随数数
    }

    /**
     * 功能描述：获取 从 [start, end) 之间的随机整数
     *
     * @param start 范围起点
     * @param end   范围终点
     *
     * @return 从 start 到 end 之间的随机整数
     */
    public static int getRandomNum(int start, int end) {
        int num = (int) (start + Math.random() * (end - start));
        return num;
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

}
