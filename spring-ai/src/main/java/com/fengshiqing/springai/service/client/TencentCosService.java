package com.fengshiqing.springai.service.client;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fengshiqing.springai.config.TencentCosConfig;
import com.fengshiqing.springai.model.BizException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 功能描述：腾讯云对象存储（Cloud Object Storage，COS）用到的配置项
 *
 * @author 冯仕清
 * @since 2025-12-29
 */
@AllArgsConstructor
@Slf4j
@Service
public class TencentCosService {

    private final COSClient cosClient;

    private final TencentCosConfig cosConfig;

    // =================================================================================================================


    /**
     * 功能描述：上传文件----前端上传的文件
     *
     * @param file 前端上传的文件
     * @return 文件的下载链接（是一个完整的可直接下载的地址，不是相对路径）
     *
     * @throws IOException 异常
     */
    public String upload(MultipartFile file, String path) throws IOException {

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new BizException(400, "【文件名不能为空，请检查】");
        }

        // 生成唯一文件名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")); // 文件类型（扩展名/后缀）
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String key = cosConfig.getPrefix() + path + uuid + ext; // 这个是文件的相对路径(桶下的路径)，下载文件时，入参传入这个 key 就能下载。

        // 设置元数据
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // 上传
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), key, file.getInputStream(), metadata);
        PutObjectResult result = cosClient.putObject(putObjectRequest);

        log.info("【upload】文件上传成功: ETag={}, Key={}", result.getETag(), key);
        return cosConfig.getUrl() + key;
    }


    public String uploadFilePath(String filePath) {
        File file = new File(filePath);
        String originalFilename = file.getName();
        if (originalFilename.isEmpty()) {
            throw new IllegalArgumentException("文件名为空");
        }
        // 生成唯一文件名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")); // 文件类型（扩展名/后缀）
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String key = cosConfig.getPrefix() + uuid + ext; // 这个是文件的相对路径(桶下的路径)，下载文件时，入参传入这个 key 就能下载。
        // 上传
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), key, file);
        PutObjectResult result = cosClient.putObject(putObjectRequest);
        log.info("【uploadFilePath】文件上传成功: ETag={}, Key={}", result.getETag(), key);
        return key;
    }


    /**
     * 功能描述：上传文件----后端上传的文件
     * （直接以流的形式上传，无需在应用服务器上生成临时文件）
     *
     * @param dataList 文件数据
     * @param clazz    数据类型的 Class对象
     * @return 文件在 COS 中的 key
     */
    public <T> String upload(List<T> dataList, Class<T> clazz) {

        if (dataList.isEmpty()) {
            throw new BizException("数据库中没有可导出的数据");
        }

        String uuid = UUID.randomUUID().toString().replace("-", ""); // 定义文件名（不包含文件后缀扩展名）
        String fileName = String.format("%s.xlsx", uuid);

        // 2. 将数据转换为Excel字节数组
        byte[] excelBytes = convertToExcel(dataList, clazz, uuid);
        log.info("【upload】【Excel转换完成，文件大小：{}字节】", excelBytes.length);

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(excelBytes)) {
            // 设置元数据
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(excelBytes.length);
            metadata.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            // 生成COS key
            String key = cosConfig.getPrefix() + fileName;

            // 上传文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), key, inputStream, metadata);

            PutObjectResult result = cosClient.putObject(putObjectRequest);
            log.info("【upload】【文件上传成功】【ETag：{}】【Key：{}】", result.getETag(), key);

            // 返回 文件的key
            return key;
        } catch (Exception e) {
            log.error("【upload】【上传失败】", e);
            throw new BizException("上传文件到COS失败：" + e.getMessage());
        }
    }


    /**
     * 功能描述：上传字节数组到 COS
     *
     * @param bytes 文件字节数组
     * @param tempFlag 是否为临时文件，true是临时文件，false永久文件。
     *
     * @return 文件在 COS 中的 key
     */
    public String uploadBytes(byte[] bytes, boolean tempFlag) {
        if (bytes == null || bytes.length == 0) {
            throw new BizException("文件内容为空");
        }

        String fileName = UUID.randomUUID().toString().replace("-", "") + ".xlsx";

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            // 设置元数据
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(bytes.length);
            metadata.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            // 生成COS key
            String key = tempFlag ? cosConfig.getPrefixTemp() + fileName : cosConfig.getPrefix() + fileName;

            // 上传文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), key, inputStream, metadata);
            PutObjectResult result = cosClient.putObject(putObjectRequest);

            log.info("【uploadBytes】【文件上传成功】【ETag：{}】【Key：{}】【大小：{}字节】", result.getETag(), key, bytes.length);

            return key;
        } catch (Exception e) {
            log.error("【uploadBytes】【上传失败】", e);
            throw new BizException("上传文件到COS失败：" + e.getMessage());
        }
    }


    private <T> byte[] convertToExcel(List<T> dataList, Class<T> clazz, String uuid) {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ExcelWriter excelWriter = EasyExcel.write(outputStream, clazz).build()) {
            // 使用EasyExcel写入数据
            WriteSheet writeSheet = EasyExcel.writerSheet(uuid).build();
            excelWriter.write(dataList, writeSheet);
            excelWriter.finish();

            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("【convertToExcel】【Excel 转换失败】", e);
            throw new BizException("Excel 转换失败");
        }
    }


    // === 生成公开访问 URL（仅当 Bucket 权限为公有读时有效）===
    public String getPublicUrl(String key) {
        return cosConfig.getUrl() + key;
    }


    // === 生成临时签名 URL（推荐用于私有 Bucket）===
    public String generatePresignedUrl(String key, long expireSeconds) {
        log.info("【generatePresignedUrl】【生成临时下载链接】【key：{}】", key);

        if (this.notExists(key)) {
            throw new BizException("文件不存在");
        }

        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(cosConfig.getBucketName(), key);
        req.setExpiration(new Date(System.currentTimeMillis() + Duration.ofSeconds(expireSeconds).toMillis()));

        URL url = cosClient.generatePresignedUrl(req);
        return url.toString();
    }


    // === 下载文件（返回 InputStream，适用于后端代理下载）===
    public COSObject download(String key) {

        if (this.notExists(key)) {
            throw new BizException("文件不存在");
        }

        GetObjectRequest getObjectRequest = new GetObjectRequest(cosConfig.getBucketName(), key);
        return cosClient.getObject(getObjectRequest);
    }


    public void delete(String key) {

        if (key == null || key.isEmpty()) {
            throw new BizException("key不能为空");
        }

        key = key.replace(cosConfig.getUrl(), "");

        if (this.notExists(key)) {
            throw new BizException("文件不存在");
        }

        cosClient.deleteObject(cosConfig.getBucketName(), key);
    }


    /**
     * 功能描述：检查文件是否存在。
     *
     * @param key 待下载文件的 key，示例：/eps/283f79abe3cd4d41aff8d4796047baae.jpg
     * @return true不存在、false存在
     */
    private boolean notExists(String key) {
        try {
            cosClient.getObjectMetadata(cosConfig.getBucketName(), key);
            return false;
        } catch (Exception e) {
            // COS SDK 在对象不存在时会抛出 CosServiceException
            return true;
        }
    }


    // === 获取文件元信息 ===
    public ObjectMetadata getMetadata(String key) {
        return cosClient.getObjectMetadata(cosConfig.getBucketName(), key);
    }

}
