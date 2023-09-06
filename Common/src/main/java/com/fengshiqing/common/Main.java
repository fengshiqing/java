/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String redisKey = "PRODUCT_KEY_{0}";
        redisKey = redisKey.replaceAll("\\{" + 0 + "}", "123");
        System.out.println(redisKey);

        Stack stack = new Stack();
    }
}
