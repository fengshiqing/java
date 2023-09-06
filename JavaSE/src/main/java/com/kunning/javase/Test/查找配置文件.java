package com.kunning.javase.Test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class 查找配置文件 {

    public static void main(String[] args) throws IOException {
        File file = new File("config.properties"); // 配置文件放在项目下，这么写可以找到配置文件
        // 从当前程序启动的目录下查找文件，D:\workspace\eclipse-32bit\XXX\config.properties
        System.out.println("查找文件的路径：" + file.getAbsolutePath()); // 打印 绝对路径
        Properties prop = new Properties();
        prop.load(new FileReader("config.properties"));
        System.out.println(prop.getProperty("username"));
        System.out.println(prop.getProperty("password"));
    }

    @Test
    public void test() {
        // 获取系统属性
        Properties properties = System.getProperties();
        System.out.println(properties);
    }
}
