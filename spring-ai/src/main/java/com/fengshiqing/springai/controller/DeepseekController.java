/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class DeepseekController {

    private final DeepSeekChatModel chatModel;

    private final TokenTextSplitter tokenTextSplitter;


    @GetMapping("/chat")
    public String call(@RequestParam("message") String message) {
        return chatModel.call(message);
    }

}
