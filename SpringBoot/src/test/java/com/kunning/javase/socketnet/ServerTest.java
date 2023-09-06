/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.socketnet;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	public static final Logger LOGGER = LoggerFactory.getLogger(ServerTest.class);

	@Test
	public void test() throws IOException {
		// 1、创建ServerSocket对象，绑定到本机特定端口的服务器套接字。
		ServerSocket serverSocket = new ServerSocket(8080);
		LOGGER.info("【服务器已成功启动】【serverSocket{}】", serverSocket);
		while (true) {
			// 2、侦听并接受到此套接字的连接，调用accept()，获取Socket对象
			Socket socket = serverSocket.accept();
			// 3、接收获取客户端发过来的套接字的输入流。
			InputStream inputStream = socket.getInputStream();
			// 4、对获取到的输入流进行处理
			byte[] byteArr = new byte[128];
			StringBuilder builder = new StringBuilder();

			int length;
			while ((length = inputStream.read(byteArr)) != -1) {// length=inputStream.read()这种写法会报错：内存溢出
				// new String(byteArr, 0, length)); 使用平台的默认字符集解码指定的 byte 子数组，构造一个新的 String。
				builder.append(new String(byteArr, 0, length));
			}
			System.out.println("服务端收到客户端发来的消息是：" + builder.toString());
			System.out.println("客户端的ip：" + socket.getInetAddress().getHostAddress());

			// 5、关闭ServerSocket、Socket、InputStream流

		}
	}

}
