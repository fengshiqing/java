package com.kunning.javase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 功能描述：JDBC连接数据库
 * 
 * @author 冯仕清
 * @since 2016/03/20 19:23
 */
public class JDBC {

    public static void main(String[] args) throws Exception {
        // 1、注册数据库驱动
        // DriverManager.registerDriver(new Driver());//
        // 这行代码有两个问题：1、com.mysql.jdbc.Driver类中有静态代码块，加载时就会自动注册到驱动管理器中；2与mysql驱动类强耦合
        Class.forName("com.mysql.jdbc.Driver");// 第一步的进阶版
        // 2、获取数据库连接
        Connection con = DriverManager
                .getConnection("jdbc:mysql://192.168.0.100:3306/fengshiqing?user='root'&password=feng234800");
        // 3、创建传输器对象
        Statement stat = con.createStatement();
        // 4、利用传输器对象传输sql语句到数据库中执行
        ResultSet rs = stat.executeQuery("select * from t_user");
        // 5、遍历结果集
        while (rs.next()) {
            System.out.println("username: " + rs.getString("username"));
        }
        // 6、关闭资源
        rs.close();
        stat.close();
        con.close();
    }

}
