/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.service;

import com.kunning.springcloud.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：使用 jdbcTemplate 进行CURD
 *
 * @author 冯仕清
 * @since 2022-07-20
 */
@AllArgsConstructor
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final JdbcTemplate jdbcTemplate;

    private final ProductMapper productMapper;

    public int save(Product product) {
        return jdbcTemplate.update("INSERT INTO products(name, code, price) values(?, ? , ?)", product.getName(), product.getCode(), product.getPrice());
    }

    public int update(Product product) {
        return jdbcTemplate.update("UPDATE products SET name = ? , code = ? , price = ? WHERE id=?", product.getName(), product.getCode(), product.getPrice(), product.getId());
    }

    public int delete(long id) {
        return jdbcTemplate.update("DELETE FROM products where id = ? ", id);
    }

    /**
     * 功能描述：根据产品ID查询产品详情信息。
     * 先查缓存，再查数据库，数据库中查到再回写到缓存，数据库中也不存在就在缓存中放个null值，防止DDOS攻击。
     *
     * @param id 产品ID
     *
     * @return 产品详情信息
     */
    public Product queryUserInfo(long id) {
        LOGGER.info("【queryProductInfo】【start】【id:{}】", id);
        return productMapper.selectByPrimaryKey(id);
    }

    public List<Product> queryUserByPage(int pageNo, int pageSize) {
        int offset  = (pageNo - 1) * pageSize;
        return productMapper.selectProductByPage(offset, pageSize);
    }

}
