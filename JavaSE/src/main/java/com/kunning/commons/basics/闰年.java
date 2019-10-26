package com.kunning.commons.basics;

//闰年  java300问的第31问
import java.util.Scanner;

public class 闰年 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            long year;
            System.out.print("请输入一个闰年年份：");
            try {
                year = scan.nextLong();
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    System.out.println(year + "年是闰年");
                    break;
                } else {
                    System.out.println(year + "年不是闰年，请重新输入");
                }
            } catch (Exception e) {
                System.out.println("你输入的不是有效年份");
                break;
            }
            // scan.close();
        }
        scan.close();
    }
}
