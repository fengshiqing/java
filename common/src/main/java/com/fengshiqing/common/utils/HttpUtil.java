/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.common.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 功能描述：JDK 21 HTTP客户端工具类
 * 基于java.net.http.HttpClient封装
 *
 * @author 冯仕清
 * @since 2025-08-31
 */
public final class HttpUtil {

    /**
     * 私有构造函数，防止实例化
     */
    private HttpUtil() {
        // 工具类，防止实例化
    }


    private static final HttpClient HTTP_CLIENT;

    static {
        // 创建自定义线程池用于异步请求
        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        // 初始化默认的HttpClient实例
        HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(30))
                .executor(executor)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }


    /**
     * 获取默认的HttpClient实例
     *
     * @return HttpClient
     */
    public static HttpClient getHttpClient() {
        return HTTP_CLIENT;
    }


    /**
     * 创建自定义的HttpClient实例
     *
     * @return HttpClient.Builder
     */
    public static HttpClient.Builder createCustomClientBuilder() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(30))
                .followRedirects(HttpClient.Redirect.NORMAL);
    }


    /**
     * 发送同步GET请求
     *
     * @param url 请求URL
     * @return HttpResponse<String>
     */
    public static HttpResponse<String> get(String url) throws IOException, InterruptedException {
        return get(url, Map.of());
    }


    /**
     * 发送同步GET请求（带请求头）
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return HttpResponse<String>
     */
    public static HttpResponse<String> get(String url, Map<String, String> headers)
            throws IOException, InterruptedException {
        HttpRequest request = buildRequest(url, "GET", headers, null);
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }


    /**
     * 发送同步POST请求
     *
     * @param url  请求URL
     * @param body 请求体
     * @return HttpResponse<String>
     */
    public static HttpResponse<String> post(String url, String body)
            throws IOException, InterruptedException {
        return post(url, Map.of(), body);
    }


    /**
     * 发送同步POST请求（带请求头）
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param body    请求体
     * @return HttpResponse<String>
     */
    public static HttpResponse<String> post(String url, Map<String, String> headers, String body)
            throws IOException, InterruptedException {
        HttpRequest request = buildRequest(url, "POST", headers, body);
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }


    /**
     * 发送异步GET请求
     *
     * @param url 请求URL
     * @return CompletableFuture<HttpResponse < String>>
     */
    public static CompletableFuture<HttpResponse<String>> getAsync(String url) {
        return getAsync(url, Map.of());
    }


    /**
     * 发送异步GET请求（带请求头）
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return CompletableFuture<HttpResponse < String>>
     */
    public static CompletableFuture<HttpResponse<String>> getAsync(String url, Map<String, String> headers) {
        HttpRequest request = buildRequest(url, "GET", headers, null);
        return HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }


    /**
     * 发送异步POST请求
     *
     * @param url  请求URL
     * @param body 请求体
     * @return CompletableFuture<HttpResponse < String>>
     */
    public static CompletableFuture<HttpResponse<String>> postAsync(String url, String body) {
        return postAsync(url, Map.of(), body);
    }


    /**
     * 发送异步POST请求（带请求头）
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param body    请求体
     * @return CompletableFuture<HttpResponse < String>>
     */
    public static CompletableFuture<HttpResponse<String>> postAsync(String url, Map<String, String> headers, String body) {
        HttpRequest request = buildRequest(url, "POST", headers, body);
        return HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }


    /**
     * 发送请求并返回字节数组（适用于下载文件）
     *
     * @param url 请求URL
     * @return HttpResponse<byte [ ]>
     * @throws IOException          输入输出异常
     * @throws InterruptedException 中断异常
     */
    public static HttpResponse<byte[]> getAsBytes(String url) throws IOException, InterruptedException {
        HttpRequest request = buildRequest(url, "GET", null, null);
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofByteArray());
    }


    /**
     * 构建HttpRequest
     *
     * @param url     请求URL
     * @param method  请求方法
     * @param headers 请求头
     * @param body    请求体
     * @return HttpRequest
     */
    private static HttpRequest buildRequest(String url, String method, Map<String, String> headers, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30));

        // 设置请求方法
        switch (method.toUpperCase()) {
            case "GET":
                builder.GET();
                break;
            case "POST":
                builder.POST(body == null ?
                        HttpRequest.BodyPublishers.noBody() :
                        HttpRequest.BodyPublishers.ofString(body));
                break;
            case "PUT":
                builder.PUT(body == null ?
                        HttpRequest.BodyPublishers.noBody() :
                        HttpRequest.BodyPublishers.ofString(body));
                break;
            case "DELETE":
                builder.DELETE();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        // 设置请求头
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(builder::header);
        }

        // 如果没有设置Content-Type，默认设置为application/json
        if (("POST".equals(method) || "PUT".equals(method)) && body != null &&
                (headers == null || !headers.containsKey("Content-Type"))) {
            builder.header("Content-Type", "application/json");
        }

        return builder.build();
    }
}