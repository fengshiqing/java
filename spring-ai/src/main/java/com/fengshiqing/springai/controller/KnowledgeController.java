package com.fengshiqing.springai.controller;

import com.alibaba.fastjson2.JSON;
import com.fengshiqing.springai.constant.BaseResponse;
import com.fengshiqing.springai.constant.ErrorCode;
import com.fengshiqing.springai.constant.ResultUtils;
import com.fengshiqing.springai.dao.entity.AliOssFile;
import com.fengshiqing.springai.model.dto.QueryFileDTO;
import com.fengshiqing.springai.service.AliOssFileService;
import com.fengshiqing.springai.service.client.TencentCosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述：上传文件，并将文件内容向量化存储。
 *
 * @author 冯仕清
 * @since 2025/2/8 20:35
 */
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/knowledge")
@Tag(name = "KnowledgeController", description = "知识库管理接口")
public class KnowledgeController {

    // 添加 <artifactId>spring-ai-starter-vector-store-milvus</artifactId> 依赖后，MilvusVectorStoreAutoConfiguration.java 会自动注入这个对象
    private final VectorStore vectorStore;

    private final TencentCosService tencentCosService;

    private final TokenTextSplitter tokenTextSplitter;

    private final AliOssFileService aliOssFileService;

    // =================================================================================================================


    /**
     * 上传附件接口
     *
     * @param files 文件
     * @return 响应
     */
    @Operation(summary = "upload", description = "上传附件接口")
    @PostMapping(value = "file/upload", headers = "content-type=multipart/form-data")
    public BaseResponse upload(@RequestParam("file") List<MultipartFile> files) {

        if (files == null || files.isEmpty()) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "请上传文件");
        }

        // 上传文件
        for (MultipartFile file : files) {
            try {
                // 向量化，主要分为如下几个阶段：
                // 1. 读取文件。我们用的 spring-ai提供的 Tika工具，支持：PDF、DOC/DOCX、PPT/PPTX、HTML。txt应该也支持，可以测试一下。
                Resource resource = file.getResource();
                TikaDocumentReader reader = new TikaDocumentReader(resource);
                List<Document> documents = reader.read();
                // 2. 分词。我们用 spring-ai提供的 TokenTextSplitter工具，这个歌分词器的策略是 根据 默认的chunk_size来切分，还能尽量保证分词不会断句。
                List<Document> splitDocuments = tokenTextSplitter.apply(documents);
                // 3. 向量化 并 保存向量。保存向量时，会 自动调用向量模型向量化方法
                vectorStore.add(splitDocuments);

                // 持久化到数据库
                String url = tencentCosService.upload(file, "rag/");
                Date date = new Date(System.currentTimeMillis());
                AliOssFile aliOssFile = AliOssFile.builder()
                        .fileName(file.getOriginalFilename()) // 原文件名
                        .vectorId(JSON.toJSONString(splitDocuments.stream().map(Document::getId).collect(Collectors.toList())))
                        .url(url)
                        .createTime(date)
                        .updateTime(date)
                        .build();
                aliOssFileService.insert(aliOssFile);

            } catch (IOException e) {
                log.error("上传文件失败", e);
                return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "上传文件失败");
            } catch (Exception e) {
                log.error("上传文件失败", e);
                return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "向量化失败");
            }
        }

        return ResultUtils.success("文件上传成功");
    }


    @Operation(summary = "contents",description = "文件查询")
    @GetMapping("/contents")
    public BaseResponse queryFiles(QueryFileDTO request){
        if(request.getPage() == null || request.getPageSize() == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"page 或 pageSize为空");
        }
        return aliOssFileService.queryPage(request);
    }

    @Operation(summary = "delete",description = "文件删除")
    @DeleteMapping("/delete")
    public BaseResponse deleteFiles(@RequestParam List<Long> ids){
        return aliOssFileService.deleteFiles(ids);
    }


    @Operation(summary = "download",description = "文件下载")
    @GetMapping("/download")
    public BaseResponse downloadFiles(@RequestParam List<Long> ids){
        return aliOssFileService.downloadFiles(ids);
    }





}
