package com.kunning.javase.Test;

import org.junit.jupiter.api.Test;

import java.util.*;

public class BasicJava {

    // 基本数据类型
    @Test
    public void javaDataType() {
        // float f = 3.14;
        // int i = 'a';
        // short x = 32761;
        // x = x + 1;
        Double d1 = new Double(-1.0 / 0.0);
        Double d2 = new Double(0.0 / 0.0);
        // returns true if this Double value is a Not-a-Number (NaN)
        System.out.println(d1 + " = " + d1.isNaN());
        // returns false for other cases
        System.out.println(d2 + " = " + d2.isNaN());
        char ch = '\u03C0';
        System.out.println(ch);
    }

    @Test
    public void exception() {
        try {
            int x = 4 / 0;
            System.out.println("x=" + x);
        } catch (Exception e) {
            System.out.println("除零啦");
            System.out.println(e.getMessage());// 输出：/ by zero
            System.out.println(e);// e.toString()
            e.printStackTrace();// 打印异常名称，异常信息，异常出现的位置
            // JVM 默认的异常处理机制，就是调用printstackTrace方法
            // 打印异常的堆栈跟踪信息
        }
        System.out.print("over");
    }

    @Test
    public void testString() throws Exception {
        String s1 = "a";
        String s2 = "bc";
        String s3 = "abcabc";
        //
        // String a1 = new String("abc");
        // String a2 = new String("abc");
        // String b1 = "abc";
        // String b2 = "abc";
        //
        // System.out.println(a1==a2);//false，new出来的每个对象的引用都不同
        // System.out.println(a1=="abc");//false
        // System.out.println(b1==b2);//true，字符串常量池
        // System.out.println(a1.equals(b1));//true
        // System.out.println();
        //
        // System.out.println("a"==a1.substring(0, 1));//false，这个不相等，
        // System.out.println("a"==b1.substring(0, 1));//false，这个不相等，
        // System.out.println("abc"=="a".concat("bc"));//false，这个不相等，
        // System.out.println("abc"=="a".concat(s2));//false，这个不相等，
        // System.out.println("abc"==s1.concat("bc"));//false，这个不相等，
        // System.out.println("abc"==s1.concat(s2));//false，这个不相等，
        // System.out.println();
        //
        // String c1 = "a" + "bc";//字面量串联而成的字符串受“字符串池”控制
        // String c2 = s1 + s2;//变量串联而成的字符串受“堆”控制
        // System.out.println(c1==a1);//false
        // System.out.println(c1==b1);//true
        // System.out.println(c2==a1);//false
        // System.out.println(c2==b1);//false
        // System.out.println();
        //
        // System.out.println(s3==a1+a2);//false，操作变量名而得到的字符串都存在堆中，不管这个变量是引用堆还是池。
        // System.out.println(s3==b1+b2);//false
        // System.out.println(s3=="abc"+"abc");//true，直接操纵字面量，得到的对象就在池中
        // System.out.println();
        //
        // System.out.println(a1.hashCode());//只要字面量的值相等，hashcode就相等。
        // System.out.println(a2.hashCode());
        // System.out.println(b1.hashCode());
        // System.out.println(b2.hashCode());
        // System.out.println("abc".hashCode());
        // System.out.println();
        StringBuffer buffer = new StringBuffer();
        buffer.append(s1 + ",");
        buffer.append(s2);
        System.out.println(buffer.toString());
    }

    @Test
    public void testArray() {
        String[] strArr = { "a", "e", "f", "g", "h", "i", "b", "c", "d" };
        System.out.println(".toString=" + Arrays.toString(strArr));// 打印出数组中所有数据
        System.out.println(".asList=" + Arrays.asList(strArr));
        Arrays.sort(strArr);// 对数组进行排序，使用的是快速排序法
        System.out.println(".toString=" + Arrays.toString(strArr));// 打印排序后数组中所有数据
        Arrays.sort(strArr, Collections.reverseOrder());// 对数组进行 倒序
        System.out.println(".asList=" + Arrays.toString(strArr));
        int flag = Arrays.binarySearch(strArr, "b");// 查找数组中 元素 的位置(数组下标从 0 开始)
        System.out.println("b的所在位置：" + flag);
        String[] str2 = new String[4];
        Arrays.fill(str2, "w");// 为数组中每个数据同初值
        System.out.println("str2[]=" + Arrays.toString(str2));
        String[][] s1 = { { "a", "b", "c", "d" }, { "a", "b", "e", "f" } };
        System.out.println("s1[][]=" + Arrays.deepToString(s1));// 打印出二维数组中的全部数据
        String[] s2 = { "a", "b", "c", };
        String[] s3 = { "c", "d", "e", "f", "w", "g", "q" };
        List<String> s4 = new ArrayList<String>();
        for (String s : s2) { // 取出两个数组的交集
            for (String value : s3) {
                if (s.equals(value)) {
                    s4.add(s);
                }
            }
        }
        System.out.println("s2[]与s3[]的交集=" + s4.toString());
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("AA", 65);
        map.put("BB", 76);
        map.put("CC", 89);
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for (Map.Entry<String, Integer> o : set) {
            System.out.println(o.getKey() + "-------->" + o.getValue());
        }

        // Object[] array = new Object[Integer.MAX_VALUE /2]; // 数组的长度是有限制的
    }

    @Test
    public void testScanner() {
        int A, B, C;
        Scanner input = new Scanner(System.in);// 控制台输入
        System.out.print("请输入");
        A = input.nextInt();
        B = input.nextInt();
        C = input.nextInt();
        System.out.println("您输入的是" + input);
        input.close();
        System.out.println(A + B + C);
    }

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
        byte[] byteArr = new byte[2];
        // String str = new String(byteArr, 0, 3);//
        // java.lang.StringIndexOutOfBoundsException: String index out of range: 3
        String str = new String(byteArr, 0, 2);
        System.out.println(str);
    }
}
