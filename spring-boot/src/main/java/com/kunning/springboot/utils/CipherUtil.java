/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;


/**
 * 功能简述：密码工具类。 MD5摘要、Base64编码。
 *
 * @author 冯仕清
 * @since 2019/12/19
 */
public final class CipherUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CipherUtil.class);

    /**
     * 私有化构造函数
     */
    private CipherUtil() {
    }

    // =====================================================MD5摘要=====================================================

    /**
     * 功能描述：生成 MD5摘要。
     *
     * @param plainStr 明文
     *
     * @return MD5摘要，是一个32个16进制的数字字母(小写)组成的字符串，每个十六进制位是4为二进制，所以MD5摘要是32*4=128位。
     */
    public static String getMd5Digest(String plainStr) {
        // plainStr可以为空字符串""，空字符串""的 MD5摘要 是：d41d8cd98f00b204e9800998ecf8427e
        Objects.requireNonNull(plainStr, "【plainStr 不能为空！】");
        byte[] byteArr = plainStr.getBytes();
        byte[] rtnByteArr = DigestUtils.md5Digest(byteArr);
        return DigestUtils.md5DigestAsHex(byteArr);
    }

    /**
     * 功能描述：生成 MD5摘要。
     *
     * @param plainStr 明文
     * @param salt 盐值
     *
     * @return MD5摘要
     */
    public static String getMd5Digest(String plainStr, String salt) {
        Objects.requireNonNull(plainStr, "【plainStr 不能为空！】");
        byte[] byteArr = StringUtils.hasLength(salt) ? (plainStr + salt).getBytes() : plainStr.getBytes();
        return DigestUtils.md5DigestAsHex(byteArr);
    }

    // =================================================base64编码、解码=================================================

    /**
     * 功能描述：进行 base64编码。
     *
     * @param plainStr 明文
     *
     * @return base64编码后的字符串
     */
    public static String encodeBase64(String plainStr) {
        byte[] byteArr = plainStr.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(byteArr);
    }

    /**
     * 功能描述：进行 base64解码。
     *
     * @param codeStr 编码后的字符串
     *
     * @return 解码后的原文
     */
    public static String decodeBase64(String codeStr) {
        byte[] byteArr = Base64.getDecoder().decode(codeStr); // Spring提供的方法默认的编码格式就是UTF-8
        return new String(byteArr, StandardCharsets.UTF_8);
    }

    // ===================================================DES对称加解密===================================================

    private static final Key key;
    private static final String KEY_STR = "myKey";
    private static final String ALGORITHM = "DES";

    static {
        try {
            // 生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // 运用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置上密钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成密钥对象
            key = generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：获取DES加密后的密文
     *
     * @param plainStr 明文
     *
     * @return 密文
     */
    public static String getEncryptString(String plainStr) {
        // 基于BASE64编码，接收byte[]并转换成String
        try {
            // 按utf8编码
            byte[] bytes = plainStr.getBytes(StandardCharsets.UTF_8);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[]to encode好的String 并返回
            return Base64.getEncoder().encodeToString(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ====================================================AES非对称加解密====================================================

    /**
     * 功能描述：AES加密后，再进行base64编码。 参考：<a href="https://blog.csdn.net/TangHao_0226/article/details/80264572">...</a>
     *
     * @param plainStr 明文
     * @param secretKey 密钥
     *
     * @return 密文
     *
     * @throws Exception 异常
     */
    public static String enCrypt(String plainStr, String secretKey) throws Exception {
        Objects.requireNonNull(plainStr, "【plainStr 不能为空！】");
        Objects.requireNonNull(secretKey, "【secretKey 不能为空！】");

        // 判断Key是否为16位
        if (secretKey.length() != 16) {
            LOGGER.error("【Key长度必须是16位！】");
            return null;
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 算法/模式/补码方式
        IvParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes()); // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encrypted = cipher.doFinal(plainStr.getBytes()); // 加密

        return Base64.getEncoder().encodeToString(encrypted); // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * 功能描述：AES解密
     *
     * @param ciphertext 密文
     * @param secretKey 密钥
     *
     * @return 解密后的字符串
     */
    public static String deCrypt(String ciphertext, String secretKey) {
        try {
            // 判断Key是否正确
            if (secretKey == null) {
                LOGGER.error("【secretKey 不能为空！】");
                return null;
            }
            // 判断Key是否为16位
            if (secretKey.length() != 16) {
                LOGGER.error("【Key长度必须是16位！】");
                return null;
            }

            byte[] encrypted = Base64.getDecoder().decode(ciphertext); // 先用base64解码
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] original = cipher.doFinal(encrypted); // 解密
            return new String(original);
        } catch (Exception e) {
            LOGGER.error("【deCrypt】【发生异常】", e);
            return null;
        }
    }

    // =================================================================================================================

    private static final String HMAC_MD_5 = "HmacMD5";
    private static final String HMAC_SHA_256 = "HmacSHA256";

    /**
     * 功能描述：初始化HMAC密钥
     * 
     * @return 字符串
     * @throws Exception 异常
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(HMAC_MD_5);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 功能描述：生成明文的摘要信息
     *
     * @param plainStr 明文
     * @param secret 密钥
     *
     * @return 密文字符串
     */
    public static String getHmacSHA256(final String plainStr, final String secret) throws GeneralSecurityException {
        Mac mac = Mac.getInstance(HMAC_SHA_256);
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA_256);
        mac.init(secretKey);
        byte[] byteArr = mac.doFinal(plainStr.getBytes(StandardCharsets.UTF_8)); // byte数组长度：32
        return Hex.encodeHexString(byteArr, true); // 将byte数组转换为16进制的字符串，字母都是大写的，总长度64
    }

}
