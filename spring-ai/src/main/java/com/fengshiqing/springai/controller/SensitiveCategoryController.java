package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.entity.SensitiveCategory;
import com.fengshiqing.springai.service.SensitiveCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @Title: SensitiveCategoryController
 * @author 冯仕清
 * @Package com.fengshiqing.springai.controller
 * @description: 敏感词分类控制器
 */



@Tag(name = "SensitiveCategoryController", description = "敏感词分类控制器")
@Slf4j
@RestController
@RequestMapping("/category")
public class SensitiveCategoryController {
    @Autowired
    private SensitiveCategoryService sensitiveCategoryService;

    // 新增接口
    @Operation(summary = "新增敏感词分类")
    @PostMapping("/add")
    public BaseResponse<Boolean> create(@RequestBody SensitiveCategory entity) {
        entity.setCreatedTime(LocalDate.now());
        entity.setUpdateTime(LocalDate.now());
        entity.setStatus("1");
        sensitiveCategoryService.save(entity);
        return ResultUtils.success(true);
    }

    // 批量删除接口
    @Operation(summary = "批量删除")
    @DeleteMapping("/batch")
    public BaseResponse<Boolean> batchDelete(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            sensitiveCategoryService.delete(id);
        }
        return ResultUtils.success(true);
    }

    // 修改接口
    @Operation(summary = "修改敏感词")
    @PutMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody SensitiveCategory entity) {
        entity.setUpdateTime(LocalDate.now());
        sensitiveCategoryService.update(entity);
        return ResultUtils.success(true);
    }

    // 分页查询接口
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public BaseResponse<List<SensitiveCategory>> page(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        // 这里需要实现分页查询敏感词分类的方法
        List<SensitiveCategory> result = new ArrayList<>();
        return ResultUtils.success(result);
    }

    // 列表查询接口
    @Operation(summary = "获取全部列表")
    @GetMapping("/list")
    public BaseResponse<List<SensitiveCategory>> list() {
        return ResultUtils.success(sensitiveCategoryService.list());
    }



}
