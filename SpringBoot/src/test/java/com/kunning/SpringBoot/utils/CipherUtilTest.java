package com.kunning.springboot.utils;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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

    @Test
    public void getMD5() {
        System.out.println("【摘要：】" + CipherUtil.getMd5Digest("123456"));

        // 字符串 + null，null对象会变为"null"字符串

        System.out.println("【摘要：】" + CipherUtil.getMd5Digest("123456", "abcdefg"));
    }

    @Test
    public void base64() {
        String plainStr = "123456冯仕清";
        String codeStr = CipherUtil.encodeBase64(plainStr);
        System.out.println("【Base64编码：】" + codeStr);
        System.out.println("【Base64解码：】" + Arrays.toString(Base64Utils.decodeFromString(codeStr)));
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

}