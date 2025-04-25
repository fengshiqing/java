package com.kunning.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class CronService {

    public String queryCron() {
        return "0 0 0/1 * * ?";// 每3秒执行一次
//        return "0/3 * * * * ?";// 每3秒执行一次
    }

}
