/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.kunning.springboot.service.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：HttpExchange 客户端。
 *
 * @author 冯仕清
 * @since 2026-01-31
 */
@HttpExchange("/product")
public interface ProductClient {

    // 场景一：根据 ID 查询
    // 对应 curl：curl -X GET http://localhost:8080/product/101
    @GetExchange("/{id}")
    Map<String, String> queryById(@PathVariable Long id);


    // 场景二：复杂关键索索
    // 对应 curl：curl -X GET http://localhost:8080/product/search?keyword=手机&minPrice=1000
    @GetExchange("search")
    List<Map<String, String>> search(@RequestParam("keyword") String keyword,
                                     @RequestParam(value = "minPrice", required = false) Long minPrice);


    // 场景三：根据条件查询
    // 对应 curl：curl -X POST http://localhost:8080/product/queryByCondition -H 'Content-Type: application/json' -d '{"keyword": "手机", "minPrice": "1000"}'
    @PostExchange("/queryByCondition")
    Map<String, String> queryByCondition(@RequestBody Map<String, String> param);


    // 场景四：更新资源
    // 对应 curl：curl -X PUT http://localhost:8080/product/update/101 -H 'Content-Type: application/json' -d '{"price": "999.99"}'
    @PutExchange("/update/{id}")
    List<Map<String, String>> updatePrice(@PathVariable Long id,
                                          @RequestBody Map<String, String> param);


    // 场景五：删除资源
    // 对应 curl：curl -X DELETE http://localhost:8080/product/delete/101
    @DeleteExchange("/delete/{id}")
    List<Map<String, String>> delete(@PathVariable Long id);


    // 场景六：上传文件
    // 对应 curl：curl -X POST http://localhost:8080/product/upload -F 'file=@/path/to/file'
    @PostExchange(value = "/upload", contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file);

}
