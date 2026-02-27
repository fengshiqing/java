package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.entity.SensitiveCategory;
import com.fengshiqing.springai.dao.SensitiveCategoryMapper;
import com.fengshiqing.springai.service.SensitiveCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Title: SensitiveCategoryController
 * @author 冯仕清
 *
 * @description: 敏感词分类控制器
 */
@Tag(name = "SensitiveCategoryController", description = "敏感词分类控制器")
@Slf4j
@RestController
@RequestMapping("/category")
public class SensitiveCategoryController {
    @Autowired
    private SensitiveCategoryService sensitiveCategoryService;
    
    @Autowired
    private SensitiveCategoryMapper sensitiveCategoryMapper;

    // 新增接口
    @Operation(summary = "新增敏感词分类")
    @PostMapping("/add")
    public BaseResponse<Boolean> create(@RequestBody SensitiveCategory entity) {
        entity.setCreatedTime(LocalDate.now());
        entity.setUpdateTime(LocalDate.now());
        entity.setStatus("1");
        int result = sensitiveCategoryService.insert(entity);
        return ResultUtils.success(result > 0);
    }

    // 批量删除接口
    @Operation(summary = "批量删除")
    @DeleteMapping("/batch")
    public BaseResponse<Boolean> batchDelete(@RequestBody List<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            count += sensitiveCategoryService.deleteById(id);
        }
        return ResultUtils.success(count > 0);
    }

    // 修改接口
    @Operation(summary = "修改敏感词")
    @PutMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody SensitiveCategory entity) {
        entity.setUpdateTime(LocalDate.now());
        SensitiveCategory existing = sensitiveCategoryService.selectById(entity.getId());
        if (existing != null) {
            int result = sensitiveCategoryMapper.insert(entity);
            return ResultUtils.success(result > 0);
        }
        return ResultUtils.success(false);
    }

    // 分页查询接口
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public BaseResponse<Map<String, Object>> page(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        int offset = (page - 1) * size;
        List<SensitiveCategory> list = sensitiveCategoryMapper.selectByCondition(offset, size);
        long total = sensitiveCategoryMapper.countAll();
        
        Map<String, Object> result = new HashMap<>();
        result.put("records", list);
        result.put("total", total);
        result.put("current", page);
        result.put("size", size);
        
        return ResultUtils.success(result);
    }

    // 列表查询接口
    @Operation(summary = "获取全部列表")
    @GetMapping("/list")
    public BaseResponse<List<SensitiveCategory>> list() {
        return ResultUtils.success(sensitiveCategoryService.selectAll());
    }



}
