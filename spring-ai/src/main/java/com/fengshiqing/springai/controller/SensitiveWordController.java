package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.entity.SensitiveWord;
import com.fengshiqing.springai.dao.SensitiveWordMapper;
import com.fengshiqing.springai.service.SensitiveWordService;
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
 * @Title: SenSitiveWordController
 * @author 冯仕清
 *
 * @description: 敏感词控制器
 */

@Tag(name = "SensitiveWordController", description = "敏感词控制器")
@Slf4j
@RestController
@RequestMapping("/sensitive")
public class SensitiveWordController {

    @Autowired
    private SensitiveWordService sensitiveWordService;
    
    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Operation(summary = "新增敏感词")
    @PostMapping("/add")
    public BaseResponse addSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        log.info("新增敏感词：{}", sensitiveWord);
        sensitiveWord.setStatus("1");
        sensitiveWord.setCreatedAt(LocalDate.now().toString());
        sensitiveWord.setUpdatedAt(LocalDate.now().toString());
        int result = sensitiveWordService.insert(sensitiveWord);
        if (result > 0){
            return ResultUtils.success(true);
        }
        return ResultUtils.error("新增失败");
    }

    @Operation(summary = "删除敏感词")
    @DeleteMapping("/{id}")
    public boolean deleteSensitiveWord(@PathVariable Integer id) {
        return sensitiveWordService.deleteById(id) > 0;
    }

    @Operation(summary = "批量删除敏感词")
    @PostMapping("/batch")
    public BaseResponse deleteSensitiveWords(@RequestBody List<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            count += sensitiveWordService.deleteById(id);
        }
        if (count > 0){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Operation(summary = "更新敏感词")
    @PutMapping
    public boolean updateSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        SensitiveWord existing = sensitiveWordService.selectById(sensitiveWord.getId());
        if (existing != null) {
            return sensitiveWordMapper.insert(sensitiveWord) > 0;
        }
        return false;
    }

    @Operation(summary = "分页查询敏感词")
    @GetMapping("/page")
    public BaseResponse<Map<String, Object>> getSensitiveWordPage(@RequestParam int page, @RequestParam int size) {
        int offset = (page - 1) * size;
        List<SensitiveWord> list = sensitiveWordMapper.selectByCondition(offset, size);
        long total = sensitiveWordMapper.countAll();
        
        Map<String, Object> result = new HashMap<>();
        result.put("records", list);
        result.put("total", total);
        result.put("current", page);
        result.put("size", size);
        
        return ResultUtils.success(result);
    }

    @Operation(summary = "查询所有敏感词")
    @GetMapping
    public List<SensitiveWord> getAllSensitiveWords() {
        return sensitiveWordService.selectAll();
    }


}
