package com.fengshiqing.springai.controller;

import com.fengshiqing.springai.config.aspect.Loggable;
import com.fengshiqing.springai.config.BaseContext;
import com.fengshiqing.springai.dao.entity.SensitiveWord;
import com.fengshiqing.springai.service.SensitiveWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "AI对话接口（带RAG）", description = "AI对话接口（带RAG）")
@Slf4j
@RestController
@RequestMapping("/ai")
public class AiRagController {

    // 对话代理
    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    @Autowired
    private SensitiveWordService sensitiveWordService;


    // 引入了 <artifactId>spring-ai-alibaba-starter-dashscope</artifactId> 后， DashScopeChatAutoConfiguration 就自动配置了一个 dashscopeChatModel。
    public AiRagController(ChatModel chatModel, ChatMemory chatMemory, VectorStore vectorStore) {
        this.chatClient = ChatClient.builder(chatModel)
                // 设置系统提示词。隐式
                .defaultSystem("""
                        你是“XS”知识库系统的对话助手，请以乐于助人的方式进行对话
                        今天的日期：{current_data}
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(), // CHAT MEMORY
                       // PromptChatMemoryAdvisor.builder(chatMemory).build(), // 记住上下文
                        SimpleLoggerAdvisor.builder().build() // 为了避免不知道大模型具体发送了什么数据，这里可以打印日志
                )
                .build();
        this.vectorStore = vectorStore;
    }


    @Operation(summary = "rag", description = "Rag对话接口")
    @GetMapping(value = "/rag")
    @Loggable
    public Flux<String> generate(@RequestParam(value = "message", defaultValue = "你好") String message) {

        // 敏感词过滤
        List<SensitiveWord> list = sensitiveWordService.selectAll();

        for (SensitiveWord sensitiveWord : list) {
            if (message.contains(sensitiveWord.getWord())) {
                return Flux.just("包含敏感词:" + sensitiveWord.getWord());
            }
        }

        Long userId = BaseContext.getCurrentId();

        return chatClient.prompt()
                .user(message)  // 用户提示词 显式
                .advisors(a -> a.param("current_data", LocalDate.now().toString()))
                //.call() // 同步方式
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, userId))
                .advisors(QuestionAnswerAdvisor.builder(vectorStore)
                        .searchRequest(
                                SearchRequest.builder()
                                        .query(message)
                                        .similarityThreshold(0.1d).topK(5)
                                        .build()
                        )
                        .build())
                .stream()// 流式方式
                .content();
    }
}