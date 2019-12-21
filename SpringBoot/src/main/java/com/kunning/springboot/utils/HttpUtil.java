package com.kunning.springboot.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 功能描述：Http工具类。
 *
 * @author 冯仕清
 */
public class HttpUtil {

    /**
     * 私有化构造函数
     */
    private HttpUtil() {
    }

    /**
     * 功能描述：GET请求
     *
     * @param httpUrl 请求地址，不负责拼接参数
     * @return 结果字符串
     */
    public static String doGet(String httpUrl) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;// 返回结果字符串

        try {
            URL url = new URL(httpUrl); // 创建远程url连接对象（1、装车）
            connection = (HttpURLConnection) url.openConnection(); // 通过远程url连接对象打开一个连接，强转成httpURLConnection类（2、打开大门路障）
            connection.setRequestMethod("GET"); // 设置连接方式：get
            connection.setConnectTimeout(15000); // 设置连接主机服务器的超时时间：15000毫秒
            connection.setReadTimeout(60000); // 设置读取远程返回的数据时间：60000毫秒

            connection.connect(); // 发送请求（3、发车）

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream(); // 通过connection连接，获取输入流
                // 封装成缓冲流，并指定字符集
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                StringBuilder stringBuilder = new StringBuilder(); // 存放数据
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                    stringBuilder.append("\r\n");
                }
                result = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                connection.disconnect();// 关闭远程连接
            }
        }

        return result;
    }

    public static String doPost(String httpUrl, String param) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;

        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection(); // 通过远程url连接对象打开连接
            connection.setRequestMethod("POST"); // 设置连接请求方式
            connection.setConnectTimeout(15000); // 设置连接主机服务器超时时间：15000毫秒
            connection.setReadTimeout(60000); // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setDoOutput(true); // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoInput(true); // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");

            outputStream = connection.getOutputStream(); // 通过连接对象获取一个输出流
            outputStream.write(param.getBytes()); // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream(); // 通过连接对象获取一个输入流，向远程读取
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                StringBuilder stringBuilder = new StringBuilder();
                String temp;
                // 循环遍历一行一行读取数据
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                    stringBuilder.append("\r\n");
                }
                result = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            if (connection != null) {
                connection.disconnect();// 关闭远程连接
            }
        }
        return result;
    }


    /**
     * org.apache.commons.httpclient和org.apache.http.client区别(转)
     * 官网说明：http://hc.apache.org/httpclient-3.x/
     *
     * Commons HttpClient项目现已结束，不再开发。
     * 它已被其HttpClient和HttpCore模块中的Apache HttpComponents项目所取代，它们提供更好的性能和更大的灵活性。
     * 从2011年开始，org.apache.commons.httpclient就不再开发。这就是说，它已经落伍了。
     *
     * 方法的对称性上的区别
     *
     * 一、org.apache.http.client
     * 官网：http://hc.apache.org/
     * org.apache.http.client 在发起请求前，假如对某个参数a 进行url encode编码。服务端必须进行url decode。
     *
     * //客户端编码
     * Stirng a=URLEncoder.encode(cont,"GBK");
     *
     * //服务端解码
     * URLDecoder.decode(a,"gbk");
     * 且服务器端获取到的参数a为可识别的没有任何变动的url encode后原值。
     *
     *
     *
     * 二、org.apache.commons.httpclient
     * org.apache.commons.httpclient则与之相反。
     * 服务端获取到的a为不可识别的乱码，且不能用url decode解码。
     * //服务端解码
     * new String(cont.getBytes("ISO8859_1"), "GBK")
     *
     *
     *
     * 与时俱进
     * org.apache.http.client更好的性能和更大的灵活性。
     * 可以很方便的支持json，xml等数据的传输。且http://mvnrepository.com上在不断的升级。超时、最大连接数等配置灵活方便。
     *
     *
     * 个人建议非必要情况，程序员还是使用org.apache.http.client较好
     * 原文：https://blog.csdn.net/wcf2010/article/details/80268570
     *
     */






    public static String doGet1(String url) {
            CloseableHttpClient httpClient = HttpClients.createDefault(); // 使用默认配置创建一个 httpClient 实例
            HttpGet httpGet = new HttpGet(url); // 创建httpGet远程连接实例
            // 设置请求头信息，鉴权
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);


        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // 执行get请求得到返回对象
//            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭资源
//            if (null != response) {
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
