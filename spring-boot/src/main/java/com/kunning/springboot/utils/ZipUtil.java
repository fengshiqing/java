/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import com.kunning.springboot.model.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 功能描述：压缩包工具类。
 * <p>
 * 参考：<a href="https://blog.csdn.net/u013066244/article/details/72783575" />
 *
 * @author fengshiqing
 * @since 2019-11-17
 */
@Slf4j
public class ZipUtil {

    /**
     * 私有化构造函数
     */
    private ZipUtil() {
    }

    // =======================================================压缩=======================================================

    /**
     * 功能描述：归档压缩文件。
     *
     * @param toDoCompressPath 带压缩的文件夹全路径（包含路径、文件夹名）
     *
     * @return 压缩包路径
     */
    public static String archive(String toDoCompressPath) {

        File file = null;
        FileOutputStream fileOutputStream = null;
        TarArchiveOutputStream tarArchiveOutStream = null;
        try {
            file = new File(toDoCompressPath);
            fileOutputStream = new FileOutputStream(file.getAbsolutePath() + ".tar");
            tarArchiveOutStream = new TarArchiveOutputStream(fileOutputStream);
            if (file.isDirectory()) {
                archiveDir(file, tarArchiveOutStream);
            } else {
                archiveHandle(tarArchiveOutStream, file);
            }
        } catch (IOException e) {
            log.error("【compressArchive】【happened IOException!】", e);
        } finally {
            IOUtils.closeQuietly(tarArchiveOutStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return file.getAbsolutePath() + ".tar";
    }

    /**
     * 递归处理，准备好路径
     * 
     * @param file 文件
     * @param tos 流
     *
     */
    private static void archiveDir(File file, TarArchiveOutputStream tos) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File fi : listFiles) {
                if (fi.isDirectory()) {
                    archiveDir(fi, tos);
                } else {
                    archiveHandle(tos, fi);
                }
            }
        }
    }

    /**
     * 具体归档处理（文件）
     * 
     * @param tos 流
     * @param fi 文件
     *
     */
    private static void archiveHandle(TarArchiveOutputStream tos, File fi) throws IOException {
        TarArchiveEntry tEntry = new TarArchiveEntry(fi.getName());
        tEntry.setSize(fi.length());

        tos.putArchiveEntry(tEntry);

        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(fi.toPath()));

        byte[] buffer = new byte[1024];
        int read;
        while ((read = bis.read(buffer)) != -1) {
            tos.write(buffer, 0, read);
        }
        bis.close();
        tos.closeArchiveEntry();// 这里必须写，否则会失败
    }

    /**
     * 把tar包压缩成gz
     * 
     * @param path 路径
     */
    public static String compressArchive(String path) {

        FileInputStream fileInputStream = null;
        BufferedInputStream bis = null;
        GzipCompressorOutputStream gcos = null;
        try {
            fileInputStream = new FileInputStream(path);
            bis = new BufferedInputStream(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(path + ".gz");
            gcos = new GzipCompressorOutputStream(new BufferedOutputStream(fileOutputStream));

            byte[] buffer = new byte[1024];
            int read;
            while ((read = bis.read(buffer)) != -1) {
                gcos.write(buffer, 0, read);
            }
        } catch (IOException e) {
            log.error("【compressArchive】【happened IOException!】", e);
        } finally {
            IOUtils.closeQuietly(gcos);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(fileInputStream);
        }
        return path;
    }

    // =======================================================解压=======================================================

    /**
     * 功能描述：解压.tar.gz压缩包
     *
     * @param tarGzFilePath .tar.gz文件的全路径
     * @param outPath 输出路径
     * @param singFileSizeLimit 单个文件的大小上限
     * @param numLinit 文件数量上限
     */
    public static void unCompressTarGz(String tarGzFilePath, String outPath, long singFileSizeLimit, int numLinit) {

        File file = new File(tarGzFilePath);

        FileInputStream fileInStream = null;
        BufferedInputStream bufferedInStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutStream = null;
        GzipCompressorInputStream gzipCompressorInStream = null;

        boolean failFlag = false; // 解压失败标记，true解压失败、false解压没失败

        String tarFileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        String tarFilePath = file.getParent() + File.separator + tarFileName;

        try {
            fileInStream = new FileInputStream(file);
            bufferedInStream = new BufferedInputStream(fileInStream);

            fileOutputStream = new FileOutputStream(tarFilePath);
            bufferedOutStream = new BufferedOutputStream(fileOutputStream);
            gzipCompressorInStream = new GzipCompressorInputStream(bufferedInStream);

            long singleFileSize = 0L;
            int len;
            byte[] byteArr = new byte[1024];
            while ((len = gzipCompressorInStream.read(byteArr)) != -1) {
                singleFileSize += len;
                if (singleFileSize > singFileSizeLimit * numLinit) { // 校验所有文件大小
                    log.error("【压缩包过大，超过上限:{}】", singFileSizeLimit * numLinit);
                    throw new BizException(500, "【压缩包过大!】");
                }
                bufferedOutStream.write(byteArr, 0, len);
            }
            // IOUtils.closeQuietly(bufferedOutStream);
        } catch (IOException e) {
            log.error("【unCompressTarGz】【happened IOException!】", e);
        } catch (BizException e) {
            failFlag = true;
            log.error("【unCompressTar】【happened BizException!】", e);
        } finally {
            IOUtils.closeQuietly(bufferedOutStream);
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(gzipCompressorInStream);
            IOUtils.closeQuietly(bufferedInStream);
            IOUtils.closeQuietly(fileInStream);
            if (!file.delete()) {
                log.error("【删除 原.tar.gz 压缩包 失败！】");
            }
            if (failFlag) {
                if (!new File(tarFilePath).delete()) {
                    log.error("【删除 解压不完全的.tar 压缩包 失败！】");
                }
            } else {
                unCompressTar(tarFilePath, outPath, 1024 * 1024 * 10L, 100); // 单个文件大小上限10M
            }
        }

    }

    /**
     * 功能描述：解压tar
     *
     * @param tarFilePath .tar压缩包名
     * @param singFileSizeLimit 单个压缩文件大小的上限
     * @param numLinit 压缩包中的子文件数量上限
     */
    public static void unCompressTar(String tarFilePath, String outPath, long singFileSizeLimit, int numLinit) {

        File file = new File(tarFilePath);
        FileInputStream fileInStream = null;
        TarArchiveInputStream tarArchiveInStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutStream = null;

        boolean failFlag = false; // 解压失败标记，true解压失败、false解压没失败

        try {
            fileInStream = new FileInputStream(file);
            tarArchiveInStream = new TarArchiveInputStream(fileInStream);

            TarArchiveEntry tarArchiveEntry;
            for (int i = 0; (tarArchiveEntry = tarArchiveInStream.getNextTarEntry()) != null; i++) {
                if (i > numLinit) { // 校验文件数量
                    log.error("【压缩包中的文件数量过多，超过上限:{}】", numLinit);
                    throw new BizException(500, "【压缩包中的文件数量过多!】");
                }

                String name = tarArchiveEntry.getName();
                File tarFile = new File(outPath, name);

                fileOutputStream = new FileOutputStream(tarFile);
                bufferedOutStream = new BufferedOutputStream(fileOutputStream);

                long singleFileSize = 0L;
                int len;
                byte[] buffer = new byte[1024 * 8];
                while ((len = tarArchiveInStream.read(buffer)) != -1) {
                    singleFileSize += len;
                    if (singleFileSize > singFileSizeLimit) { // 校验单个文件大小
                        log.error("【压缩包中单个子文件过大，超过上限:{}】", singFileSizeLimit);
                        throw new BizException(500, "【压缩包中单个子文件过大!】");
                    }
                    bufferedOutStream.write(buffer, 0, len);
                }
                if (singleFileSize > singFileSizeLimit * numLinit) { // 校验所有文件大小
                    log.error("【压缩包过大，超过上限:{}】", numLinit);
                    throw new BizException(500, "【压缩包过大!】");
                }
            }
            tarArchiveInStream.close();
        } catch (IOException e) {
            log.error("【unCompressTar】【happened IOException!】", e);
        } catch (BizException e) {
            failFlag = true;
            log.error("【unCompressTar】【happened BizException!】", e);
        } finally {
            // 1、关闭流
            IOUtils.closeQuietly(bufferedOutStream);
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(tarArchiveInStream);
            IOUtils.closeQuietly(fileInStream);
            // 2、删除解压未完成的临时文件
            if (failFlag) {
                // TODO
            }
        }
        file.delete();// 删除tar文件
    }

}
