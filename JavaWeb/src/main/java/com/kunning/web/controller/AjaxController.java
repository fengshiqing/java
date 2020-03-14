package com.kunning.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 功能描述：处理 Ajax 请求
 *
 * @author fengshiqing冯仕清
 */
@RestController
public class AjaxController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);

    @RequestMapping(value = "/SpringMVC/testAjax")
    public String testAjax() {
        LOGGER.info("【ajax】【开始执行】");
        return "testAJAX";
    }

    /**
     * 功能描述：处理Ajax请求
     *
     * @param map 前端传过来的参数
     */
    @PostMapping(value="/SpringMVC/dealAjaxReq")
    public void dealAjaxReq(@RequestBody Map<String, String> map) {
        LOGGER.info("【dealAjaxReq】【开始执行】【请求参数：】【map:{}】", map);
        for(Map.Entry<String, String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        LOGGER.info("【dealAjaxReq】【结束执行】");
    }

}
