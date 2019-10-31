package com.kunning.springboot.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CipherUtilTest {

    @Test
    public void getMD5() {
        String digest = CipherUtil.getMd5Digest("", "");
        System.out.println("【摘要：】" + digest);
    }

    @Test
    public void base64() {
    }
}