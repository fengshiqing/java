package com.kunning.springboot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
            connection.setConnectTimeout(15 * 1000); // 设置连接主机服务器超时时间：15000毫秒
            connection.setReadTimeout(60 * 1000); // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setDoOutput(true); // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoInput(true); // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            // 设置请求头
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

}
