/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springai.controller;

import com.fengshiqing.common.utils.HttpUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpResponse;

@RestController
public class DownloadImageController {

    @GetMapping("/downloadImage")
    public ResponseEntity<ByteArrayResource> downloadImage(@RequestParam String imageUrl) {
        try {
            // 使用工具类获取图片字节数组
            HttpResponse<byte[]> response = HttpUtil.getAsBytes(imageUrl);

            if (response.statusCode() != 200) {
                return ResponseEntity.status(response.statusCode()).build();
            }

            // 获取文件名
            String fileName = this.getFileNameFromUrl(imageUrl);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.add(HttpHeaders.CONTENT_TYPE, response.headers()
                    .firstValue("Content-Type")
                    .orElse(MediaType.APPLICATION_OCTET_STREAM_VALUE));
            headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(response.body().length));

            // 返回响应
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new ByteArrayResource(response.body()));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private String getFileNameFromUrl(String url) {
        try {
            URL parsedUrl = URI.create(url).toURL();
            String path = parsedUrl.getPath();
            String fileName = path.substring(path.lastIndexOf('/') + 1);
            return fileName.isEmpty() ? "download.jpg" : fileName;
        } catch (Exception e) {
            return "download.jpg";
        }
    }

}
