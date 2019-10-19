package com.kunning.JavaSE.basics;

public class 找素数 {

    public static void main(String args[]) {
        
        long startTime = System.currentTimeMillis();
        // 打印50以内的素数
        boolean flag;
        for (int n = 2; n <= 100000; n++) {
            flag = false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(n);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        calc();
    }
    
    public static void calc() {
        
        long startTime = System.currentTimeMillis();
        // 打印50以内的素数
        loop:for (int n = 2; n <= 100000; n++) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    continue loop;
                }
            }
            System.out.println(n);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
    
}
