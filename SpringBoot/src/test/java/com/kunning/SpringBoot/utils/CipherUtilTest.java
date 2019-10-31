package com.kunning.springboot.utils;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.util.Base64Utils;

import static org.junit.Assert.*;

public class CipherUtilTest {

    @Test
    public void getMD5() {
        String digest = CipherUtil.getMd5Digest(null, "");
        System.out.println("【摘要：】" + digest);
    }

    @Test
    public void base64() {
    }

    @Test
    public void Encrypt() throws Exception {
        System.out.println("【加密：】" + CipherUtil.Encrypt("123", "1234123412341234", "0102030405060708"));
        System.out.println("【解密：】" + CipherUtil.Decrypt("yxjD73tZf9daDDzsACDxEg==", "1234123412341234", "0102030405060708"));
    }

}