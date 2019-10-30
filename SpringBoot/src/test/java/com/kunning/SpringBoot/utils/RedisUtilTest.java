package com.kunning.springboot.utils;

import com.kunning.springboot.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) // junit提供的注解，表示该类是单元测试的执行类
@SpringBootTest(classes = Application.class) //是spring-test提供的测试执行单元类(是Spring单元测试中SpringJUnit4ClassRunner的新名字)
public class RedisUtilTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void aaa() {
        redisTemplate.opsForValue().set("hello11", "111");
        System.out.println("【hello】" + redisTemplate.opsForValue().get("hello"));
    }

}