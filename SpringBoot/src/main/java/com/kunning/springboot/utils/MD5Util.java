package com.kunning.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * 功能描述：MD5 摘要工具类。
 */
public class MD5Util {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 功能描述：获取 MD5 摘要。
     *
     * @param plaintext 明文
     * @param salt 盐值
     *
     * @return MD5摘要
     */
    public static String getMD5(String plaintext, String salt) {
        if (StringUtils.isEmpty(plaintext)) {
            return null;
        }
        byte[] byteArr = StringUtils.isEmpty(salt) ? plaintext.getBytes() : (plaintext + salt).getBytes();
        return DigestUtils.md5DigestAsHex(byteArr);
    }

    public static void main(String[] args) {
        getMD5("123", null);
    }
}
