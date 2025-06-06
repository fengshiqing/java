/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.mapper;

import com.fengshiqing.springcloud.mapper.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    ProductEntity selectByPrimaryKey(Long id);

    List<ProductEntity> selectProductByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int updateByPrimaryKey(ProductEntity record);
}