package com.kunning.web.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 功能描述：SqlSession工具类。
 *
 * orm工具的基本思想：
 * 无论是用过的hibernate、mybatis，你都可以发现他们有一个共同点：
 * 1. 从配置文件(通常是XML配置文件中)获取配置信息，然后构造 sessionfactory
 * 2. 由sessionfactory 产生 session
 * 3. 在session 中完成对数据的增删改查和事务提交等
 * 4. 用完之后关闭session
 * 5. 在java 对象和 数据库之间有做mapping 的配置文件，也通常是xml 文件。
 *
 * @author 冯仕清
 */
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 获取SqlSessionFactory
     *
     * @return SqlSessionFactory
     */
    private static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            try {
                InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 读取数据库连接信息
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); // 构造一个SqlSessionFactory
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSession() {
        return getSqlSessionFactory().openSession(); // 这里并没有创建真正的数据库连接，只是mybatis创建了一个表示连接的对象
    }

}
