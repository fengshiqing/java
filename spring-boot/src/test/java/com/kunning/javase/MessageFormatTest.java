package com.kunning.javase;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

public class MessageFormatTest {

    @Test
    public void testCompose() {
        System.out.println(MessageFormat.format("【我说{0}，{1}，{2}】", 11, 22, 33));
        System.out.println(MessageFormat.format("【我说{0}，{2}，{3}】", 11, 22, 33));
        System.out.println(MessageFormat.format("【我说{0}，{0}，{2}】", 11, 22, 33));
    }

}
