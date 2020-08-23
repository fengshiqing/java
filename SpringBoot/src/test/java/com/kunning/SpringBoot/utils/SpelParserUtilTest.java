/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.utils;

import org.junit.jupiter.api.Test;

class SpelParserUtilTest {

    @Test
    void getKey() {
        String key = "#lisi1";
        String[] nameArr = new String[] {"zhangsan", "lisi"};
        Object[] objArr = new Object[] {"真人-1", "真人-2"};
        System.out.println(SpelParserUtil.getKey(key, nameArr, objArr));
    }

}