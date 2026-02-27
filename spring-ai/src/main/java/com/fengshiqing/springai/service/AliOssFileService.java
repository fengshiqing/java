/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.alibaba.fastjson2.JSON;
import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ErrorCode;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.AliOssFileMapper;
import com.fengshiqing.springai.dao.entity.AliOssFile;
import com.fengshiqing.springai.model.dto.QueryFileDTO;
import com.fengshiqing.springai.service.client.TencentCosService;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 冯仕清
* @description 针对表【ali_oss_file】的数据库操作Service实现
* @createDate 2025-02-08 20:51:33
*/
@Service
public class AliOssFileService {

    @Autowired
    private AliOssFileMapper aliOssFileMapper;

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    private TencentCosService tencentCosService;


    /**
     * 查询文件
     * @param request
     * @return
     */
    public BaseResponse queryPage(QueryFileDTO request) {
        int offset = (request.getPage()) * request.getPageSize();
        List<AliOssFile> fileList = aliOssFileMapper.findByFileNameContaining(
                request.getFileName(), offset, request.getPageSize());
        long total = aliOssFileMapper.countByFileName(request.getFileName());
        
        Map<String, Object> result = new HashMap<>();
        result.put("records", fileList);
        result.put("total", total);
        result.put("current", request.getPage());
        result.put("size", request.getPageSize());
        
        return ResultUtils.success(result);
    }


    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteFiles(List<Long> ids) {
        List<AliOssFile> aliOssFiles = aliOssFileMapper.selectByIds(ids);
        if (ids.isEmpty()) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "请选择文件");
        }
        int count = aliOssFileMapper.deleteBatchIds(ids);
        if (count == 0) {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR, "删除失败");
        }
        for (AliOssFile aliOssFile : aliOssFiles) {
            List<String> vectorIds = JSON.parseArray(aliOssFile.getVectorId(), String.class);
            vectorStore.delete(vectorIds);
            tencentCosService.delete(aliOssFile.getUrl());
        }

        return ResultUtils.success("成功删除"+ count + "个文件");
    }


    public BaseResponse downloadFiles(List<Long> ids) {
        List<AliOssFile> aliOssFiles = aliOssFileMapper.selectByIds(ids);
        if (ids.isEmpty()) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "请选择文件");
        }
        for (AliOssFile aliOssFile : aliOssFiles){
            String url = aliOssFile.getUrl();
            String fileName = extractFileName(url);
//            aliOssUtil.download(fileName);
        }
        return ResultUtils.success("下载成功");
    }


    public int insert(AliOssFile aliOssFile) {
        return aliOssFileMapper.insert(aliOssFile);
    }


    public static String extractFileName(String url) {
        // 找到最后一个斜杠的位置
        int lastSlashIndex = url.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            return url; // 如果没有找到斜杠，返回整个URL
        }
        // 从最后一个斜杠之后的部分截取
        return url.substring(lastSlashIndex + 1);
    }

}




