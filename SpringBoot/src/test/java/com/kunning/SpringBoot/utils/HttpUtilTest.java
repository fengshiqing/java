package com.kunning.springboot.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class) // junit提供的注解，表示该类是单元测试的执行类
public class HttpUtilTest {

    @Test
    public String httpClient() throws IOException {
        HttpRequest request = new BasicHttpRequest("GET", "/", HttpVersion.HTTP_1_1);

        System.out.println(request.getRequestLine().getMethod());
        System.out.println(request.getRequestLine().getUri());
        System.out.println(request.getProtocolVersion());
        System.out.println(request.getRequestLine().toString());


        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }

        InputStream inputStream = entity.getContent();
        int length;
        StringBuilder stringBuilder = new StringBuilder();
        byte[] byteArr = new byte[2048];
        while ((length = inputStream.read(byteArr)) != -1) {
            // new String(byteArr, 0, length)); 使用平台的默认字符集解码指定的 byte 子数组，构造一个新的 String。
            stringBuilder.append(new String(byteArr, 0, length));
        }
        return stringBuilder.toString();
    }


    @Test
    public void doGet() {
        String respStr = HttpUtil.doGet("http://whois.pconline.com.cn/ipJson.jsp?json=true");
        System.out.println("【respStr：】" + respStr);
    }

    @Test
    public void doPost() {
        String respStr = HttpUtil.doPost("http://whois.pconline.com.cn/ipJson.jsp?json=true", "");
        System.out.println("【respStr：】" + respStr);
    }

}