/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.controller;

import com.fengshiqing.common.bean.Resp;
import com.fengshiqing.common.bean.RespData;
import com.fengshiqing.springcloud.mapper.entity.ProductEntity;
import com.fengshiqing.springcloud.service.ProductService;
import com.fengshiqing.springcloud.service.dto.ReqPaged;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：产品controller。
 *
 * @author 冯仕清
 * @since 2022-07-20
 */
@AllArgsConstructor
@RestController
@Validated
public class ProductController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @RequestMapping("/product/save")
    public Resp save() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCode("iphone 11");
        productEntity.setProductName("iphone 11");
        productEntity.setOriginalPrice(BigDecimal.valueOf(100L));
        int num = productService.save(productEntity);
        LOGGER.info("【save】【success】【num:{}】", num);
        return new Resp(200, "保存成功");
    }

    @RequestMapping("/product/update")
    public Resp update() {
        long pid = 1;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCode("iphone 12");
        productEntity.setProductName("iphone 12");
        productEntity.setOriginalPrice(BigDecimal.valueOf(200L));
        productEntity.setId(pid);
        int num = productService.update(productEntity);
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
    public RespData<ProductEntity> queryProductInfo(long id) {
        LOGGER.info("【queryProductInfo】【start】【id:{}】", id);
        ProductEntity productEntity = productService.queryProductInfo(id);
        return new RespData<>(productEntity);
    }

    /**
     * 功能描述：分页查询产品列表数据。
     * @param reqPaged 请求参数
     *
     * @return 产品列表数据
     */
    @PostMapping("/product/queryProductByPage")
    public RespData<List<ProductEntity>> queryProductByPage(@RequestBody  ReqPaged reqPaged) {
        LOGGER.info("【queryProductByPage】【start】【req:{}】", reqPaged);

        long start = System.currentTimeMillis();
        List<ProductEntity> productEntityList = productService.queryProductByPage(reqPaged.getPageNo(), reqPaged.getPageSize());
        // { "pageSize": 10, "pageNo": 10000 } 表里存了100w数据，这样查询需要 50ms左右的时间
        // { "pageSize": 10, "pageNo": 100000 } 表里存了100w数据，这样查询需要 300+ms时间
        // 一般查询1000条数据，不会有啥影响的，大分页也得1w以后的页才会受点影响。
        LOGGER.info("【queryProductByPage】【花费时间：{}】", System.currentTimeMillis() - start);
        return new RespData<>(productEntityList);
    }
}
