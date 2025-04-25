/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.controller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@ResponseBody // 表示返回值都写到响应体里，具体看这里：https://www.cnblogs.com/xuchao0506/p/9556130.html
public class CuratorLockController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorLockController.class);

    @Autowired
    private CuratorFramework client;

    /**
     * 锁测试共享变量
     */
    private Integer lockCount = 1000;

    @GetMapping("/nolock")
    public String nolock() {
        // 业务逻辑
        lockCount--;
        LOGGER.info("【lockCount值:{}】", lockCount);
        return "【】";
    }

    @GetMapping("/lock")
    public String lock() throws Exception {
        // Zk分布式锁
        InterProcessMutex lock = new InterProcessMutex(client, "/lock/lockCount");

        if (lock.acquire(5, TimeUnit.SECONDS)) { // 锁过期时间为：5秒
            try {
                if (lockCount <= 0) {
                    throw new Exception("lockCount不能小于0");
                }
                // 业务逻辑
                lockCount--;
                LOGGER.info("【lockCount值:{}】", lockCount);
                return "【获取锁成功】";
            } finally {
                lock.release();
            }
        }
        return "【获取锁失败】";
    }

}
