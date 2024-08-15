/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.controller;

import com.fengshiqing.common.bean.Resp;
import com.fengshiqing.common.bean.RespData;
import com.fengshiqing.springcloud.service.Product;
import com.fengshiqing.springcloud.service.ProductService;
import com.fengshiqing.springcloud.service.Req;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述：产品controller。
 *
 * @author 冯仕清
 * @since 2022-07-20
 */
@AllArgsConstructor
@RestController
public class ProductController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @RequestMapping("/product/save")
    public Resp save() {
        Product product = new Product();
        product.setCode("iphone 11");
        product.setName("iphone 11");
        product.setPrice(100);
        int num = productService.save(product);
        LOGGER.info("【save】【success】【num:{}】", num);
        return new Resp(200, "保存成功");
    }

    @RequestMapping("/product/update")
    public Resp update() {
        long pid = 1;
        Product product = new Product();
        product.setCode("iphone 12");
        product.setName("iphone 12");
        product.setPrice(200);
        product.setId(pid);
        int num = productService.update(product);
        LOGGER.info("【update】【success】【num:{}】", num);
        return new Resp(200, "修改成功");
    }

    @RequestMapping("/product/delete")
    public Resp delete(long pid) {
        int num = productService.delete(pid);
        LOGGER.info("【delete】【success】【num:{}】", num);
        return new Resp(200, "删除成功");
    }


    /**
     * 功能描述：分页查询产品列表数据。
     * @param id 请求参数
     *
     * @return 产品列表数据
     */
    @GetMapping("/product/queryProductInfo")
    public RespData<Product> queryProductInfo(long id) {
        LOGGER.info("【queryProductInfo】【start】【id:{}】", id);
        Product product = productService.queryProductInfo(id);
        return new RespData<>(product);
    }

    /**
     * 功能描述：分页查询产品列表数据。
     * @param req 请求参数
     *
     * @return 产品列表数据
     */
    @PostMapping("/product/queryProductByPage")
    public RespData<List<Product>> queryProductByPage(@RequestBody Req req) {
        LOGGER.info("【queryProductByPage】【start】【req:{}】", req);

        long start = System.currentTimeMillis();
        List<Product> productList = productService.queryProductByPage(req.getPageNo(), req.getPageSize());
        // { "pageSize": 10, "pageNo": 10000 } 表里存了100w数据，这样查询需要 50ms左右的时间
        // { "pageSize": 10, "pageNo": 100000 } 表里存了100w数据，这样查询需要 300+ms时间
        // 一般查询1000条数据，不会有啥影响的，大分页也得1w以后的页才会受点影响。
        LOGGER.info("【queryProductByPage】【花费时间：{}】", System.currentTimeMillis() - start);
        return new RespData<>(productList);
    }
}
