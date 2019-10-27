package com.kunning.commons.algorithms;

import org.junit.Test;

import static org.junit.Assert.*;

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