/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.socketnet;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端，与serverSocket通信。
 */
public class ClientTest {

	@Test
	public void main() {
		Socket socket = null;
		OutputStream outputStream = null;
		try {
			socket = new Socket("127.0.0.1", 8080);// 步骤一：创建绑定到特定端口的服务器套接字。IP地址、端口号
			outputStream = socket.getOutputStream();// 步骤二：获取OutputStream输出流对象
			String requestBody = "吼吼";
			outputStream.write(requestBody.getBytes());// 步骤三：发送数据，使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			// 步骤四：关闭相应的流和Socket连接。
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
