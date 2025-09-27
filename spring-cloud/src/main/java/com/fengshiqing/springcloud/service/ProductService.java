/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service;

import com.fengshiqing.common.Constant.RedisKey;
import com.fengshiqing.springcloud.aspect.RedisCache;
import com.fengshiqing.springcloud.mapper.ProductMapper;
import com.fengshiqing.springcloud.mapper.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate;

    private final ProductMapper productMapper;


    public int save(ProductEntity productEntity) {
        return jdbcTemplate.update("INSERT INTO t_product(product_name, product_code, original_price) values(?, ? , ?)",
                productEntity.getProductName(), productEntity.getProductCode(), productEntity.getOriginalPrice());
    }

    public int update(ProductEntity productEntity) {
        return jdbcTemplate.update("UPDATE t_product SET product_name = ? , product_code = ? , original_price = ? WHERE id=?",
                productEntity.getProductName(), productEntity.getProductCode(), productEntity.getOriginalPrice(), productEntity.getId());
    }

    public int delete(long id) {
        return jdbcTemplate.update("DELETE FROM t_product where id = ? ", id);
    }

    /**
     * 功能描述：根据产品ID查询产品详情信息。
     * 先查缓存，再查数据库，数据库中查到再回写到缓存，数据库中也不存在就在缓存中放个null值，防止DDOS攻击。
     *
     * @param id 产品ID
     *
     * @return 产品详情信息
     */
    @RedisCache(key = RedisKey.PRODUCT_KEY)
    public ProductEntity queryProductInfo(long id) {
        log.info("【queryProductInfo】【start】【id:{}】", id);
        return productMapper.selectByPrimaryKey(id);
    }

    public List<ProductEntity> queryProductByPage(int pageNo, int pageSize) {
        int offset  = (pageNo - 1) * pageSize;
        return productMapper.selectProductByPage(offset, pageSize);
    }

}
