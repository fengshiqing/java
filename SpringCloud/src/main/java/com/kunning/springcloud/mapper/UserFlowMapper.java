package com.kunning.springcloud.mapper;

import com.kunning.springcloud.service.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：用户流程Dao
 */
@Repository
public interface UserFlowMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectProductByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}