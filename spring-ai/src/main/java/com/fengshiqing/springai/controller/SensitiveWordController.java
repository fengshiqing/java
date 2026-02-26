package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.entity.SensitiveWord;
import com.fengshiqing.springai.service.SensitiveWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: SenSitiveWordController
 * @author 冯仕清
 * @Package com.fengshiqing.springai.controller
 * @description: 敏感词控制器
 */

@Tag(name = "SensitiveWordController", description = "敏感词控制器")
@Slf4j
@RestController
@RequestMapping("/sensitive")
public class SensitiveWordController {

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Operation(summary = "新增敏感词")
    @PostMapping("/add")
    public BaseResponse addSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        log.info("新增敏感词：{}", sensitiveWord);
        sensitiveWord.setStatus("1");
        sensitiveWord.setCreatedAt(LocalDate.now().toString());
        sensitiveWord.setUpdatedAt(LocalDate.now().toString());
        sensitiveWordService.save(sensitiveWord);
        return ResultUtils.success(true);
    }

    @Operation(summary = "删除敏感词")
    @DeleteMapping("/{id}")
    public boolean deleteSensitiveWord(@PathVariable Integer id) {
        sensitiveWordService.delete(id);
        return true;
    }

    @Operation(summary = "批量删除敏感词")
    @PostMapping("/batch")
    public BaseResponse deleteSensitiveWords(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            sensitiveWordService.delete(id);
        }
        return ResultUtils.success("删除成功");
    }

    @Operation(summary = "更新敏感词")
    @PutMapping
    public boolean updateSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        sensitiveWordService.update(sensitiveWord);
        return true;
    }

    @Operation(summary = "分页查询敏感词")
    @GetMapping("/page")
    public BaseResponse<List<SensitiveWord>> getSensitiveWordPage(@RequestParam int page, @RequestParam int size) {
        // 这里需要实现分页查询敏感词的方法
        List<SensitiveWord> result = new ArrayList<>();
        return ResultUtils.success(result);
    }

    @Operation(summary = "查询所有敏感词")
    @GetMapping
    public List<SensitiveWord> getAllSensitiveWords() {
        // 这里需要实现查询所有敏感词的方法
        return new ArrayList<>();
    }


}
