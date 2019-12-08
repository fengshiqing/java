package com.kunning.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_0 {

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        jdbcTemplate.execute("select * from user");
    }

}
