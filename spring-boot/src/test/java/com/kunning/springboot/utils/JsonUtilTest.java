/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import com.kunning.springboot.pojo.UserDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JsonUtilTest {

    @Test
    void toJsonStr() {
        UserDto userDto = new UserDto();
        userDto.setId(123456789);
        userDto.setNickname("fengshiqing");
        userDto.setPassword("123456789");
        userDto.setEmail("938481169@qq.com");

        String jsonStr = JsonUtil.toJsonStr(userDto);
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
        UserDto userDto = (UserDto) JsonUtil.fromJson(jsonStr, UserDto.class);
        System.out.println(userDto);
    }

    @Test
    void testJson(){
        List<Map<String, Object>> mapList = new ArrayList<>();
        UserDto userDto = new UserDto();
        userDto.setId(123456789);
        userDto.setNickname("fengshiqing");
        userDto.setPassword("123456789");
        userDto.setEmail("938481169@qq.com");

        Map<String, Object> map = new HashMap<>();
        map.put("111", userDto);
        map.put("222", userDto);
        map.put("333", userDto);

        mapList.add(map);
        mapList.add(map);

        System.out.println(JsonUtil.fromJson(JsonUtil.toJsonStr(mapList)));
    }

}