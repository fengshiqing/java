/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

public class CipherUtilTest {

    /**
     * 功能描述：验证字符的编码
     */
    @Test
    public void byteArr() {
        String str1 = "abc123";
        System.out.println("【byte数组：】" + Arrays.toString(str1.getBytes()));
        System.out.println("【byte数组：】" + Arrays.toString(str1.getBytes(StandardCharsets.ISO_8859_1)));
        System.out.println("【byte数组：】" + Arrays.toString(str1.getBytes(StandardCharsets.US_ASCII)));
        System.out.println("【byte数组：】" + Arrays.toString(str1.getBytes(StandardCharsets.UTF_8)));

        String str2 = "冯仕清";
        System.out.println("【byte数组：】" + Arrays.toString(str2.getBytes()));
        System.out.println("【byte数组：】" + Arrays.toString(str2.getBytes(StandardCharsets.ISO_8859_1)));
        System.out.println("【byte数组：】" + Arrays.toString(str2.getBytes(StandardCharsets.US_ASCII)));
        System.out.println("【byte数组：】" + Arrays.toString(str2.getBytes(StandardCharsets.UTF_8)));

        // 结论：字母数字等ASCII码表中有的字符，结果都是一样，汉字的话就需要注意编码格式！！！
    }

    /**
     * 功能描述L生成MD5摘要
     */
    @Test
    public void getMD5() {
        System.out.println("【摘要：】" + CipherUtil.getMd5Digest("123456"));

        // 字符串 + null，null对象会变为"null"字符串

        System.out.println("【摘要：】" + CipherUtil.getMd5Digest("123456", "abcdefg"));
    }

    /**
     * 功能描述：base64编码/解码
     */
    @Test
    public void base64() {
        String plainStr = "123456冯仕清";
        String codeStr = CipherUtil.encodeBase64(plainStr);
        System.out.println("【Base64编码：】" + codeStr);
        System.out.println("【Base64解码：】" + CipherUtil.decodeBase64(codeStr));
    }

    @Test
    public void Encrypt() throws Exception {
        String plainStr = "123456冯仕清";
        String secretKey = "1234123412341234";
        String enCryptStr = CipherUtil.enCrypt(plainStr, secretKey);
        System.out.println("【加密后的密文：】" + enCryptStr);
        System.out.println("【解密后的明文：】" + CipherUtil.deCrypt(enCryptStr, secretKey));
    }

    @Test
    public void sha256_mac() throws GeneralSecurityException {
        String outPut = CipherUtil.getHmacSHA256("abc123冯仕清", "abc");
        System.out.println(outPut);
    }

    @Test
    public void test01() {
        String str = "heima";
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            // 打印ascii码
            System.out.println(b);
            // 获取二进制位
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }

    @Test
    public void test02() {
        String str = "黑马"; // 中文UTF-8编码一个汉字占3个字节，中文GBK编码一个汉字占2个字节。
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("字节个数：" + bytes.length);
        char[] chars = str.toCharArray();
        for (char c : chars) { // 打印字符
            System.out.println(c);
            // 字符类型会自动提升为int类型，获取二进制值（10进制转二进制）
            String s = Integer.toBinaryString(c);
            System.out.println(s);
        }
    }

    @Test
    public void test001() {
        String data = "itcast";
        byte[] bytes = data.getBytes(); // 测试hex
        String encoded = Hex.encodeHexString(bytes); // 转成 16进制
        System.out.println(encoded);
    }
}