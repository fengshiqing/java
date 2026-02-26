package com.fengshiqing.springai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.model.dto.WordFrequencyPageQueryDTO;
import com.fengshiqing.springai.model.resp.RespDataList;
import com.fengshiqing.springai.service.WordFrequencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：分词统计控制器
 *
 * @author 冯仕清
 * @since 2025-03-06
 */

@Tag(name="WordFrequencyController",description = "分词统计控制器")
@Slf4j
@RestController
@RequestMapping("/frequency")
public class WordFrequencyController {
    @Autowired
    private WordFrequencyService wordFrequencyService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    // 分页条件查询
    @PostMapping("/page")
    @Operation(summary = "page", description = "分页查询")
    public RespDataList<?> pageQuery(@RequestBody WordFrequencyPageQueryDTO queryDTO) {

        return wordFrequencyService.pageQuery(queryDTO);
    }

    // 清空数据
    @DeleteMapping("/clean")
    @Operation(summary = "clean", description = "清空数据")
    public BaseResponse<String> clean() {
        wordFrequencyService.remove(null);
        return ResultUtils.success("清空成功");
    }





    @GetMapping("/getList")
    public BaseResponse<Object> getList() throws JsonProcessingException {
        String cacheKey = "wordFrequencyList";
        String cachedListStr = (String) redisTemplate.opsForValue().get(cacheKey);
        if (cachedListStr != null) {
            try {
                Object cachedList = objectMapper.readValue(cachedListStr, List.class);
                log.info("从 Redis 缓存中获取数据");
                return ResultUtils.success(cachedList);
            } catch (Exception e) {
                log.error("Redis 缓存解析失败", e);
            }
        }

        Object dataList = wordFrequencyService.list();

        String dataListJson = objectMapper.writeValueAsString(dataList);
        redisTemplate.opsForValue().set(cacheKey, dataListJson);
        redisTemplate.expire(cacheKey, 5, TimeUnit.MINUTES);

        return ResultUtils.success(dataList);
    }




}
