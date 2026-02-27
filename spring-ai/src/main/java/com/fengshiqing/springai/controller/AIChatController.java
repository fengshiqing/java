package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.config.aspect.Loggable;
import com.fengshiqing.springai.config.BaseContext;
import com.fengshiqing.springai.dao.entity.SensitiveWord;
import com.fengshiqing.springai.service.SensitiveWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Tag(name = "AI对话接口", description = "AI对话接口")
@RestController
@RequestMapping("/chat")
public class AIChatController {

    private final ChatClient chatClient;

    private SensitiveWordService sensitiveWordService;

    // =================================================================================================================


    @Operation(summary = "stream", description = "流式对话接口")
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Loggable("message")
    public Flux<String> streamRagChat(@RequestParam(value = "message", defaultValue = "你好") String message) {

        List<SensitiveWord> list = sensitiveWordService.selectAll();

        for (SensitiveWord sensitiveWord : list) {
            if (message.contains(sensitiveWord.getWord())) {
                return Flux.just("包含敏感词:" + sensitiveWord.getWord());
            }
        }

        Long userId = BaseContext.getCurrentId();
        return chatClient.prompt()
                // .system(prompt) // 系统提示词，如果没有设置的话，可以在这里设置
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, userId))
                .user(message)
                .stream()
                .content();
    }

}
