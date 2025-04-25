/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import org.junit.jupiter.api.Test;

class ZipUtilTest {

    @Test
    void compressArchive() {
        // 压缩文件
        String toDoCompressPath = "C:\\workspace\\test/TarGz"; // 需要压缩的文件夹
        String archive = ZipUtil.archive(toDoCompressPath); // 生成tar包
        System.out.println(archive);
        String path = ZipUtil.compressArchive(archive); // 生成gz包
        System.out.println(path);
    }

    @Test
    void unCompressTarGz() {
        String tarGzFilePath = "C:/workspace/test/TarGz.tar.gz";
        String outPath = "C:/workspace/test";
        long singFileSizeLimit = 1024 * 1024 * 10L;
        ZipUtil.unCompressTarGz(tarGzFilePath, outPath, singFileSizeLimit, 100);// 解压
    }

}