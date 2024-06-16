/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.controller;

import com.kunning.springcloud.controller.response.Resp;
import com.kunning.springcloud.service.Product;
import com.kunning.springcloud.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：产品controller。
 *
 * @author 冯仕清
 * @since 2022-07-20
 */
@AllArgsConstructor
@RestController
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final ProductService productService;

    @RequestMapping("/user/save")
    public Resp save() {
        Product product = new Product();
        product.setCode("iphone 11");
        product.setName("iphone 11");
        product.setPrice(100);
        int num = productService.save(product);
        LOGGER.info("【save】【success】【num:{}】", num);
        return new Resp(200, "保存成功");
    }

    @RequestMapping("/user/update")
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

    @RequestMapping("/user/delete")
    public Resp delete(long pid) {
        int num = productService.delete(pid);
        LOGGER.info("【delete】【success】【num:{}】", num);
        return new Resp(200, "删除成功");
    }

}
