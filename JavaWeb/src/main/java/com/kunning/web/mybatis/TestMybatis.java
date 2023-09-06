package com.kunning.web.mybatis;

import java.util.List;

import com.fengshiqing.common.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kunning.web.mybatis.dao.UserDao;
import com.kunning.web.mybatis.util.SqlSessionFactoryUtil;

/**
 * 功能描述：用 Mybatis 插入数据到数据库
 */
public class TestMybatis {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestMybatis.class);

    public static void main(String[] args) {
        LOGGER.info("【开始执行】");

        // 步骤一：获取Session
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();// 获取sqlsession

        // 步骤二：组装数据
//		User user = new User("fengshiqing", "feng.0905");

        // 步骤三：执行sql语句
        // 方式一：这种方式可以不写dao层接口，直接调用。但是必须将sqlMap的xml文件配置到mybatis-config.xml文件中。
//        List<User> userList = sqlSession.selectList("UserDao.getAll");

        // 方式二：这种方式需要dao层接口。sql语句甚至可以写在dao层接口的方法上（这样就不必将sqlMap的xml文件配置到mybatis-config.xml文件中。）。
        UserDao userDao = sqlSession.getMapper(UserDao.class); // 这里创建的对象是：代理对象
        List<User> userList = userDao.getAll();

        // 步骤四：commit、close
        sqlSession.commit();// 注释掉这句话，就没有提交，数据库中也就不会有变化
        sqlSession.close();
        if (userList.size() > 0) {
            LOGGER.info("【查询成功：】{}", userList);
        } else {
            LOGGER.info("【查询失败】");
        }
    }
}
