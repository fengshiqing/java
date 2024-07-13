/*
 *  Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;

@SpringBootTest(classes = CloudApplication.class)
public class EsTest {

    @Autowired
    private ElasticsearchRestTemplate template;
    /**创建索引和映射*/
    @Test
    public void createIndex(){

//        template.createIndex(ElasticSearchDto.class);
//        template.putMapping(ElasticSearchDto.class);
    }


}

