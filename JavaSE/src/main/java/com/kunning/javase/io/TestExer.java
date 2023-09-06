package com.kunning.javase.io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestExer {

    @Test
    public void copyFile() throws IOException {
        // 复制文件
        System.out.println(new File("test.txt").getAbsolutePath()); // 默认加载文件的位置是：D:\workspace\Repository-Git\java\JavaSE\test.txt
        BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("test2.txt")));

        char[] charArr = new char[20];
        int len;
        while ((len = bufferedReader.read(charArr)) != -1) {
            bufferedWriter.write(charArr, 0, len);
        }
        bufferedWriter.close();
        bufferedReader.close();

    }

    @Test
    public void readFile() throws IOException {
        // 读取文件内容
        BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
        String str;
        while ((str = bufferedReader.readLine()) != null) { // 按行读取
            System.out.println(str);
        }
        bufferedReader.close();
    }

    @Test
    public void writeFile() throws IOException {
        // 写入文件内容
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test1.txt"));
        String str = "浏览器是用来显示在万维网或局域网等内的文字、图像及其他信息的软件，它还可以让用户与这些文件进行交互操作。";
        bufferedWriter.write(str);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    @Test
    public void test1() throws IOException {
        // 写入文件内容
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("test3.txt"));
        String str = "浏览器是用来显示在万维网或局域网等内的文字、图像及其他信息的软件，它还可以让用户与这些文件进行交互操作。";
        bos.write(str.getBytes());
        bos.flush();
        bos.close();
    }
}
