/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.controller;

import com.fengshiqing.common.bean.Resp;
import com.fengshiqing.springcloud.mapper.entity.ProductEntity;
import com.fengshiqing.springcloud.mapper.entity.UserEntity;
import com.fengshiqing.springcloud.service.ProductService;
import com.fengshiqing.springcloud.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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

    private final UserService userService;

    @RequestMapping("/user/save")
    public Resp save(UserEntity userEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCode("iphone 11");
        productEntity.setProductName("iphone 11");
        productEntity.setOriginalPrice(BigDecimal.valueOf(100L));
        int num = productService.save(productEntity);
        LOGGER.info("【save】【success】【num:{}】", num);
        return new Resp(200, "保存成功");
    }

    @RequestMapping("/user/update")
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

    @RequestMapping("/user/delete")
    public Resp delete(long pid) {
        int num = productService.delete(pid);
        LOGGER.info("【delete】【success】【num:{}】", num);
        return new Resp(200, "删除成功");
    }

}
