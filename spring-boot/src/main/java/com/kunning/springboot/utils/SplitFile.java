package com.kunning.springboot.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class SplitFile {

    /**
     * 私有化构造函数
     */
    private SplitFile() {
    }

    /**
     * 功能描述：读取文件
     *
     * @throws IOException IO异常
     */
    public static void readTxt() throws IOException {
        File file = new File("D:\\workspace\\Repository-Git\\111.txt");
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        FileOutputStream fos = new FileOutputStream("D:\\workspace\\Repository-Git\\222.txt");

        int len;
        while ((len = bufferedInputStream.read()) != -1) {
            fos.write(len);
        }
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        SplitFile.readTxt();
    }

}
