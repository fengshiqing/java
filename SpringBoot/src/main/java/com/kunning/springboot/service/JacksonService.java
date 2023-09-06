/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：Jackson服务类
 */
@Service
public class JacksonService {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 功能描述：Jackson树遍历通常适合没有POJO对应的Json.
     *
     * @return 解析出来的数据
     */
    public String readTree() throws JsonProcessingException {
        String jsonStr = "{\"name\":\"fengshiqing\", \"age\":29}";
        JsonNode node = objectMapper.readTree(jsonStr);
        String name = node.get("name").asText();
        int age = node.get("age").asInt();
        return name + age;
    }

}
