package com.kunning.springboot.utils;

import com.kunning.springboot.pojo.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JsonUtilTest {

    @Test
    void toJsonStr() {
        User user = new User();
        user.setId(123456789);
        user.setNickname("fengshiqing");
        user.setPassword("123456789");
        user.setEmail("938481169@qq.com");

        String jsonStr = JsonUtil.toJsonStr(user);
        System.out.println(jsonStr);
    }

    @Test
    void fromJson() {
        String jsonStr = "{\n" +
                "  \"id\" : 123456789,\n" +
                "  \"password\" : \"123456789\",\n" +
                "  \"nickname\" : \"fengshiqing\",\n" +
                "  \"email\" : \"938481169@qq.com\"\n" +
                "}";
        User user = (User) JsonUtil.fromJson(jsonStr, User.class);
        System.out.println(user);
    }

    @Test
    void testJson(){
        List<Map<String, Object>> mapList = new ArrayList<>();
        User user = new User();
        user.setId(123456789);
        user.setNickname("fengshiqing");
        user.setPassword("123456789");
        user.setEmail("938481169@qq.com");

        Map<String, Object> map = new HashMap<>();
        map.put("111", user);
        map.put("222", user);
        map.put("333", user);

        mapList.add(map);
        mapList.add(map);

        System.out.println(JsonUtil.fromJson(JsonUtil.toJsonStr(mapList)));
    }

}