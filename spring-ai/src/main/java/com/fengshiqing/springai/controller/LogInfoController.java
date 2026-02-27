package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.entity.LogInfo;
import com.fengshiqing.springai.dao.LogInfoMapper;
import com.fengshiqing.springai.service.LogInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: LogInfoController
 * @author 冯仕清
 *
 * @description: 日志控制器
 */
@Tag(name = "LogInfoController", description = "日志控制器")
@Slf4j
@RestController
@RequestMapping("/log")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;
    
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Operation(summary = "分页查询日志信息（带条件查询）")
    @GetMapping("/page")
    public BaseResponse<Map<String, Object>> getLogInfoPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String methodName,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String requestParams) {
        
        int offset = (page - 1) * size;
        List<LogInfo> list = logInfoMapper.selectByCondition(methodName, className, requestParams, offset, size);
        long total = logInfoMapper.countByCondition(methodName, className, requestParams);
        
        Map<String, Object> result = new HashMap<>();
        result.put("records", list);
        result.put("total", total);
        result.put("current", page);
        result.put("size", size);
        
        return ResultUtils.success(result);
    }

    @Operation(summary = "批量删除日志信息")
    @PostMapping("/batch")
    public BaseResponse deleteLogInfos() {
        int result = logInfoService.deleteAll();
        if (result > 0) {
            return ResultUtils.success("删除成功");
        } else {
            return ResultUtils.error("删除失败");
        }
    }
}
