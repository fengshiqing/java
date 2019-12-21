package com.kunning.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SplitFile {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SplitFile.class);

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
