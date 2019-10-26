package com.kunning.commons.basics;

public class 测试 {
    public static void main(String[] args) {
        float f1 = 13.23f;
        // float f2 = 13.23;// 编译错误，小数默认为double类型，赋值给float必须强转
        double d1 = 4562.12;
        double d2 = 45678.1564d;
        System.out.println("f1=" + f1);
        System.out.println("d1=" + d1);
        System.out.println("d2=" + d2);

        String str1 = "abc";
        String str2 = "ABC";
        String str3 = str1.toUpperCase();
        String str4 = "ABC".toUpperCase();
        System.out.println("ABC" == str3);
        System.out.println(str2 == str3);
        System.out.println(str3 == str4);

        int[] arr = new int[] { 8, 2, 1, 0, 3 };// 号码中含有的数字
        int[] index = new int[] { 2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3 };
        String tel = "";
        for (int i : index) {
            tel += arr[i];// 1 8 0 1 8 0 1 00
        }
        System.out.println(tel);

        String s1 = "a";
        String s2 = "b";
        String str6 = s1 + s2;
        System.out.println(str6 == "ab");// false

        String s = "a";// 注意:这里s用final修饰，相当于一个常量
        String str5 = s + "b";
        System.out.println(str5 == "ab");// true
    }
}
