/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 功能描述：异步任务
 *
 * @author fengshiqing
 * @since 2022-10-09
 */
public class SynTask {
    public static final Logger LOGGER = LoggerFactory.getLogger(SynTask.class);

    /**
     * 定义线程池
     */
    private static final ExecutorService executor = new ThreadPoolExecutor(4, 9, 30, TimeUnit.MINUTES, new LinkedBlockingQueue<>(50));

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //2、定义 FutureTask 异步任务，代返回结果的异步任务
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            LOGGER.info("【】");
            return "执行结果";
        });
        // 3、执行异步任务
        executor.submit(futureTask);
        // 4、获取返回值
        String resp = futureTask.get(30, TimeUnit.MINUTES); // 最长等待30分钟
    }
}
