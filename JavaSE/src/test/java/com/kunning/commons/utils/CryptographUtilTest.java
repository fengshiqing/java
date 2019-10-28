package com.kunning.commons.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CryptographUtilTest {

    @Test
    public void getMD5() {
    }

    @Test
    public void MD5Encode() {
        String string = "你好 世界";
        String byteArrayToHexString = CryptographUtil.byteArrayToHexString(string.getBytes());
        System.out.println(byteArrayToHexString);//e68891e698afe4b880e58fa5e8af9d
    }

    @Test
    public void byteArrayToHexString() {
    }

    @Test
    public void byteToHexString() {
    }
}