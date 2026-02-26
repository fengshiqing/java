package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.entity.LogInfo;
import com.fengshiqing.springai.service.LogInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: LogInfoController
 * @author 冯仕清
 * @Package com.fengshiqing.springai.controller
 * @description: 日志控制器
 */
@Tag(name = "LogInfoController", description = "日志控制器")
@Slf4j
@RestController
@RequestMapping("/log")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;

    @Operation(summary = "分页查询日志信息（带条件查询）")
    @GetMapping("/page")
    public BaseResponse<List<LogInfo>> getLogInfoPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String methodName,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String requestParams) {
        // 这里需要实现分页查询日志的方法
        List<LogInfo> result = new ArrayList<>();
        return ResultUtils.success(result);
    }

    @Operation(summary = "批量删除日志信息")
    @PostMapping("/batch")
    public BaseResponse deleteLogInfos() {
        // 这里需要实现批量删除日志的方法
        return ResultUtils.success("删除成功");
    }
}
