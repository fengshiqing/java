package com.kunning.springboot.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilTest {

    @Test
    public void getMD5() {
        String digest = MD5Util.getMD5("", "");
        System.out.println("【摘要：】" + digest);
    }
}