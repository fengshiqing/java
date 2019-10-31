package com.kunning.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * 功能简述：密码工具类。
 * MD5摘要、Base64编码。
 */
public class CipherUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CipherUtil.class);

    /**
     * 功能描述：获取 MD5 摘要。
     *
     * @param plaintext 明文
     * @param salt      盐值
     *
     * @return MD5摘要
     */
    public static String getMd5Digest(String plaintext, String salt) {
        if (StringUtils.isEmpty(plaintext)) {
            LOGGER.error("【plaintext不能为空！】");
            return null;
        }
        byte[] byteArr = StringUtils.isEmpty(salt) ? plaintext.getBytes() : (plaintext + salt).getBytes();
        return DigestUtils.md5DigestAsHex(byteArr);
    }


    /**
     * 功能描述：base64编码
     *
     * @param plaintext 明文
     */
    public void base64(String plaintext) {
        // 用这个
        // spring-core-5.2.0.RELEASE.jar!\org\springframework\util\Base64Utils.class
    }

}
