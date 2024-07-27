package com.kunning.springcloud.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：Json工具类。
 * 一般用的只有3种：Jackson、FastJson、Gson。
 * Spring MVC 的默认 json 解析器便是 Jackson，
 * <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-json">...</a>
 * 所以我们也使用Jackson。
 * <a href="https://www.cnblogs.com/guanbin-529/p/11488869.html">...</a>，此代码文件根据这篇博客来整理的。
 * <p>
 * <a href="https://blog.csdn.net/tkwxty/article/details/34474501/">...</a>
 * <a href="http://www.jb51.net/article/104993.htm">...</a>
 *
 * @author 冯仕清
 * @since 2020-04-04
 */
@Slf4j
public class JsonUtil {

    /**
     * 私有化构造函数...
     */
    private JsonUtil() {
    }

    /**
     * 全局只调用这一个对象。
     * Jackson 最常用的 API 就是基于"对象绑定" 的 ObjectMapper。
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /*
     * 在调用 writeValue 或调用 readValue 方法之前，往往需要设置 ObjectMapper 的相关配置信息。
     * 这些配置信息应用 java 对象的所有属性上。示例如下：
     */
    static {
        //在反序列化时忽略在 json字符串 中存在，但 Java 对象不存在的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //在序列化时日期格式默认为 yyyy-MM-dd'T'HH:mm:ss.SSSZ
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //在序列化时忽略值为 null 的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略值为默认值的属性
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
    }

    /**
     * 功能描述: 序列化，将对象转成Json字符串
     *
     * @param obj Json字符串
     *
     * @return jsonStr字符串
     */
    public static String toJsonStr(Object obj) {

        String jsonString = null;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("【toJsonStr】【序列化时发生异常：】", e);
        }
        return jsonString;
    }

    /**
     * 功能描述: 反序列化，将Json字符串转换为对象
     *
     * @param jsonStr Json字符串
     * @param clazz 解析后的类型
     *
     * @return object 反序列化后的对象，json对象
     */
    public static <T> Object fromJson(String jsonStr, Class<T> clazz) {
        T object = null;
        try {
            object = objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            log.error("【fromJson】【反序列化时发生异常：】", e);
        }
        return object;
    }

    /**
     * 功能描述：反序列化，将Json字符串转换为 List<Map<String, Object>> 对象
     *
     * @param listJsonStr Json字符串
     *
     * @return mapList 反序列化后的对象，json对象
     */
    public static List<Map<String, Object>> fromJson(final String listJsonStr) {
        List<Map<String, Object>> mapList = null;// 定义返回结果集
        try {
            mapList = objectMapper.readValue(listJsonStr, objectMapper.getTypeFactory().constructCollectionType(List.class, HashMap.class));
        } catch (IOException e) {
            log.error("【fromJson】【反序列化时发生异常：】", e);
        }
        return mapList;
    }
}
