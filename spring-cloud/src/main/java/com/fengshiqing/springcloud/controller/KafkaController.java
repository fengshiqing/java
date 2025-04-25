/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.controller;

import com.fengshiqing.springcloud.service.client.KafkaClient;
import com.fengshiqing.common.bean.Resp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
public class KafkaController {

    private final KafkaClient kafkaClient;

    @GetMapping("/send")
    public Resp sendMsg(){
        kafkaClient.sendTestMsg("【------------测试消息-----------】");
        return new Resp(200, "成功");
    }

}
