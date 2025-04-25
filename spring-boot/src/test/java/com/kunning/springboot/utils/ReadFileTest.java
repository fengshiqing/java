/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ReadFileTest {

    private static final String FILE_PATH_IN = "C:\\WorkSpace\\java\\SpringBoot\\scripts/source.txt";
    private static final String FILE_PATH_OUT = "C:\\WorkSpace\\java\\SpringBoot\\scripts/dest.txt";

    @Test
    public void readFile001() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_IN));
                BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH_OUT))
        ) {
            String str;
            while ((str = reader.readLine()) != null) {
                bw.write(str);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：解决java读取大文件内存溢出问题。
     * 方式一
     */
    @Test
    public void readFile002() {

        try (FileInputStream inputStream = new FileInputStream(FILE_PATH_IN);
             Scanner sc = new Scanner(inputStream, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());;
        }
    }

}
