package com.kunning.javaSE.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);// 步骤一：创建ServerSocket对象，绑定到本机特定端口的服务器套接字。
		System.out.println(serverSocket);
		while (true) {
			Socket socket = serverSocket.accept();// 步骤二：侦听并接受到此套接字的连接，调用accept()，获取Socket对象
			InputStream inputStream = socket.getInputStream();// 步骤三：接收获取客户端发过来的套接字的输入流。
			// 4、对获取到的输入流进行处理
			byte[] byteArr = new byte[128];
			StringBuffer buffer = new StringBuffer();

			int length = 0;
			while ((length = inputStream.read(byteArr)) != -1) {// length=inputStream.read()这种写法会报错：内存溢出
				// new String(byteArr, 0, length)); 使用平台的默认字符集解码指定的 byte 子数组，构造一个新的 String。
				buffer.append(new String(byteArr, 0, length));
			}
			System.out.println("服务端收到客户端发来的消息是：" + buffer.toString());
			System.out.println("客户端的ip：" + socket.getInetAddress().getHostAddress());

			// 5、关闭ServerSocket、Socket、InputStream流

		}
	}

}
