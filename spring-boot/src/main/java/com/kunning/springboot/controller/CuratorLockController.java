/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：分布式锁。
 *
 * https://blog.csdn.net/quest101/article/details/116927935
 * https://www.cnblogs.com/bruce1992/p/13889991.html
 *
 * @author kunning
 * @since 2021-12-19
 */
@AllArgsConstructor
@Slf4j
@RestController
public class CuratorLockController {

//    private CuratorFramework client;

    /**
     * 锁测试共享变量
     */
//    private Integer lockCount = 1000;
//
//    @GetMapping("/nolock")
//    public String nolock() {
//        // 业务逻辑
//        lockCount--;
//        log.info("【lockCount值:{}】", lockCount);
//        return "【】";
//    }

    @GetMapping("/lock")
    public String lock() throws Exception {
        // Zk分布式锁
//        InterProcessMutex lock = new InterProcessMutex(client, "/lock/lockCount");
//
//        if (lock.acquire(5, TimeUnit.SECONDS)) { // 锁过期时间为：5秒
//            try {
//                if (lockCount <= 0) {
//                    throw new Exception("lockCount不能小于0");
//                }
//                // 业务逻辑
//                lockCount--;
//                log.info("【lockCount值:{}】", lockCount);
//                return "【获取锁成功】";
//            } finally {
//                lock.release();
//            }
//        }
        return "【获取锁失败】";
    }

}
