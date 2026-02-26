package com.fengshiqing.springai.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：腾讯云对象存储（Cloud Object Storage，COS）的配置类。
 * <a href="https://cloud.tencent.com/document/product/436/10199">SDK官方文档</a>
 *
 * @author 冯仕清
 * @since 2025-01-01
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "tencent.cos")
public class TencentCosConfig {

    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
    private String url;
    private String prefix;
    private String prefixTemp;


    /**
     * 构造函数
     */
    public TencentCosConfig() {
        log.info("【TencentCosConfig】【初始化配置】");
    }


    @Bean
    public COSClient cosClient() {

        COSCredentials credentials = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));

        return new COSClient(credentials, clientConfig);
    }

}
