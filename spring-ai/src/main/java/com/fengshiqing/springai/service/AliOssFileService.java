/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.alibaba.fastjson2.JSON;
import com.fengshiqing.springai.service.client.TencentCosService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ErrorCode;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.constant.PageResult;
import com.fengshiqing.springai.dao.entity.AliOssFile;
import com.fengshiqing.springai.dao.AliOssFileMapper;
import com.fengshiqing.springai.model.dto.QueryFileDTO;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Page<AliOssFile> fileList = aliOssFileMapper.findByFileNameContaining(request.getFileName());
        long total = fileList.size();
        List<AliOssFile> records = fileList.getResult();
        PageResult pageResult = new PageResult(total, records);
        return ResultUtils.success(pageResult);
    }


    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteFiles(List<Long> ids) {
        if (ids.isEmpty()) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "请选择文件");
        }
        List<AliOssFile> aliOssFiles = new ArrayList<>();
        int count = 0;
        for (Long id : ids) {
            AliOssFile aliOssFile = aliOssFileMapper.selectById(id.intValue());
            if (aliOssFile != null) {
                aliOssFiles.add(aliOssFile);
                aliOssFileMapper.deleteById(id.intValue());
                count++;
            }
        }
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
        if (ids.isEmpty()) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "请选择文件");
        }
        List<AliOssFile> aliOssFiles = new ArrayList<>();
        for (Long id : ids) {
            AliOssFile aliOssFile = aliOssFileMapper.selectById(id.intValue());
            if (aliOssFile != null) {
                aliOssFiles.add(aliOssFile);
            }
        }
        for (AliOssFile aliOssFile : aliOssFiles){
            String url = aliOssFile.getUrl();
            String fileName = extractFileName(url);
            tencentCosService.download(fileName);
        }
        return ResultUtils.success("下载成功");
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

    /**
     * 保存文件信息到数据库
     * @param aliOssFile 文件信息对象
     */
    public void save(AliOssFile aliOssFile) {
        aliOssFileMapper.insert(aliOssFile);
    }

}




