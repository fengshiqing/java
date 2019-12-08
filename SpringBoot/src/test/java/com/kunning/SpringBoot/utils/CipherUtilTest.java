package com.kunning.springboot.utils;

import org.junit.Test;

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
        System.out.println("【加密：】" + CipherUtil.Encrypt("123", "1234123412341234"));
        System.out.println("【解密：】" + CipherUtil.Decrypt("yxjD73tZf9daDDzsACDxEg==", "1234123412341234"));
    }

}