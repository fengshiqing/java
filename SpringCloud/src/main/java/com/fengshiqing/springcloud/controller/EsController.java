/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.controller;

import com.fengshiqing.springcloud.client.EsClient;
import com.fengshiqing.springcloud.utils.I18nUtil;
import com.fengshiqing.common.bean.Resp;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 功能描述：ElasticSearch的Controller
 *
 * @author fengshiqing 冯仕清
 * @since 2024-07-21
 */
@AllArgsConstructor
@RefreshScope
@RestController
public class EsController {

    private final EsClient esClient;


    /**
     * 功能描述：创建索引
     *
     * @return Resp
     * @throws IOException 异常
     */
    @GetMapping("/es/createIndex")
    public Resp createIndex() throws IOException {
        esClient.createIndex();
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：添加数据
     *
     * @return Resp
     * @throws IOException 异常
     */
    @GetMapping("/es/addData")
    public Resp addData() throws IOException {
        esClient.addData();
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：删除数据
     *
     * @return Resp
     * @throws IOException 异常
     */
    @GetMapping("/es/delDataVidId")
    public Resp delDataVidId(@RequestParam("id") String id) throws IOException {
        esClient.delDataVidId(id);
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：删除数据
     *
     * @return Resp
     * @throws IOException 异常
     */
    @GetMapping("/es/delDataVidQuery")
    public Resp delDataVidQuery(@RequestParam("value") String value) throws IOException {
        esClient.delDataVidQuery(value);
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：删除数据
     *
     * @return Resp
     * @throws IOException 异常
     */
    @GetMapping("/es/delDataVidQuery1")
    public Resp delDataVidQuery1(@RequestParam("value") String value) throws IOException {
        esClient.delDataVidQuery1(value);
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：删除数据
     *
     * @return Resp
     * @throws IOException 异常
     */
    @GetMapping("/es/updateViaId")
    public Resp updateViaId(@RequestParam("id") String id) throws IOException {
        esClient.updateViaId(id);
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：删除数据
     *
     * @return Resp
     * @throws IOException 异常
     */
    @GetMapping("/es/query")
    public Resp query(@RequestParam("value") String value) throws IOException {
        esClient.query(value);
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }

}
