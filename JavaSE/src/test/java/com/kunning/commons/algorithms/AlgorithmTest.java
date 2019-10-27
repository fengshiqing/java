package com.kunning.commons.algorithms;


import org.junit.Test;

public class AlgorithmTest {

    // =======================================================素数=======================================================
    @Test
    public void getPrimeNum() {
        Algorithm.getPrimeNum(100);
    }

    @Test
    public void isPrimeNum() {
        boolean bool = Algorithm.isPrimeNum(100);
        System.out.println("【是素数吗：】" + bool);
    }

    // =====================================================斐波那契数列=====================================================
    @Test
    public void fibonacci() {
        int fibonacciNum = Algorithm.fibonacci(10);
        System.out.println("【斐波那契数字是：】" + fibonacciNum);
    }

    // ==================================================最大公约数、最小公倍数==================================================
    @Test
    public void GCD() {// 最大公约数
        int gcdNum = Algorithm.GCD(15, 6);
        System.out.println("【最大公约数：】" + gcdNum);
    }

    @Test
    public void LCM() {// 最小公倍数
        int lcmNum = Algorithm.LCM(15, 6);
        System.out.println("【最小公倍数：】" + lcmNum);
    }

    // ======================================================随机整数======================================================
    @Test
    public void getRandomNum() {
        int fibonacciNum = Algorithm.getRandomNum(1, 10);
        System.out.println("【随机数：】" + fibonacciNum);
    }

    @Test
    public void getRandomStr() {
        String randomStr = Algorithm.getRandomStr(6);
        System.out.println("【随机数字字符串：】" + randomStr);
    }

}