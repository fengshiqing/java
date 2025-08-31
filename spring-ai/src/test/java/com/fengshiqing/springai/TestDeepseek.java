/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springai;

import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import com.fengshiqing.common.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 功能描述：应用程序入口
 *
 * @author 冯仕清
 * @since 2025-08-30
 */
@SpringBootTest(classes = AiApplication.class)
public class TestDeepseek {

    @Test
    public void deepseek(@Autowired DeepSeekChatModel model) { // 自动配置类：DeepSeekChatAutoConfiguration.class
        String respStr = model.call("你好，你是谁？");
        System.out.println(respStr); // 这里是阻塞式的输出，大模型一次性输出所有的内容，所以这里会阻塞。
    }

    @Test
    public void deepseekStream(@Autowired DeepSeekChatModel model) { // 自动配置类：DeepSeekChatAutoConfiguration.class
        Flux<String> respStr = model.stream("你好，你是谁？");
        respStr.toIterable().forEach(System.out::println);; // 这里是阻塞式的输出，大模型一次性输出所有的内容，所以这里会阻塞。
    }

    @Test
    public void deepseekChatOptions(@Autowired DeepSeekChatModel model) { // 自动配置类：DeepSeekChatAutoConfiguration.class
        DeepSeekChatOptions build = DeepSeekChatOptions.builder()
                .model("deepseek-chat") // 模型名称，在 application.properties 中配置了 deepseek-chat，这里可以自定义。
                .temperature(1.9d).build();
        String respStr = model.call("你好，请写一句诗描述清晨。");
        System.out.println(respStr); // 这里是阻塞式的输出，大模型一次性输出所有的内容，所以这里会阻塞。
        ChatResponse respStr2 = model.call(new Prompt("你好，请写一句诗描述清晨。"));
        System.out.println(respStr2.getResult().getOutput().getText()); // 这里是阻塞式的输出，大模型一次性输出所有的内容，所以这里会阻塞。
    }

    @Test
    public void text2Img(@Autowired DashScopeImageModel model) { // 自动配置类：DashScopeImageAutoConfiguration.class
        DashScopeImageOptions options = DashScopeImageOptions.builder()
                .withModel("wanx2.0-t2i-turbo") // 模型名称，在 application.properties 中配置了 wanx-v1，这里可以自定义。
                .build();
        ImageResponse imgResp = model.call(new ImagePrompt("你好，请写一句诗描述清晨。"));
        // 图片URL
        String url = imgResp.getResult().getOutput().getUrl();
        System.out.println(url); // 这里是阻塞式的输出，大模型一次性输出所有的内容，所以这里会阻塞。

        // 图片 base64 编码
        String base64 = imgResp.getResult().getOutput().getB64Json();

        // 响应文件流给前端，前端可以下载此文件
        model.getOptions().getResponseFormat().equals("base64") ? model.getOptions().getResponseFormat().equals("base64") : model.getOptions().getResponseFormat().equals("url");
    }

    public void downloadPic() {
        try {
            // 同步GET请求
            HttpResponse<String> response = HttpUtil.get("https://httpbin.org/get");
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());

            // 带请求头的同步GET请求
            Map<String, String> headers = Map.of(
                    "User-Agent", "JDK21-HttpUtils/1.0",
                    "Accept", "application/json"
            );
            HttpResponse<String> responseWithHeaders = HttpUtil.get("https://httpbin.org/get", headers);
            System.out.println("Response with headers: " + responseWithHeaders.body());

            // 同步POST请求
            String jsonBody = "{\"name\":\"John\", \"age\":30}";
            HttpResponse<String> postResponse = HttpUtil.post("https://httpbin.org/post", jsonBody);
            System.out.println("POST response: " + postResponse.body());

            // 异步GET请求
            CompletableFuture<HttpResponse<String>> future = HttpUtil.getAsync("https://httpbin.org/get");
            future.thenAccept(resp -> {
                System.out.println("Async response status: " + resp.statusCode());
            }).join(); // 等待异步操作完成

            // 文件下载
            HttpResponse<byte[]> fileResponse = HttpUtil.getAsBytes("https://httpbin.org/image/jpeg");
            System.out.println("Downloaded file size: " + fileResponse.body().length + " bytes");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
