package com.josh.node.test.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

/**
 * 测试网络
 * 
 * @author lihaotian
 *
 */
public class TestNet {

	public static void main(String[] args) {

		System.out.println("========== Test Networks ==========");

		try {
			// 上电后需要等待一会，进行网络初始化等
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		useSocket();
	}

	/**
	 * 使用Socket测试网络
	 */
	private static void useSocket() {

		System.out.println("========== Test Networks By Socket ==========");

		try {

			// 建立连接
			StreamConnection streamConnection = (StreamConnection) Connector.open("socket://www.baidu.com:80");

			System.out.println("========== Socket Connect Success ==========");

			OutputStream outputStream = streamConnection.openOutputStream();
			InputStream inputStream = streamConnection.openInputStream();

			// 发送请求
			String request = "GET / HTTP/1.0\n\n";
			outputStream.write(request.getBytes());
			outputStream.close();

			int len = 0;
			byte[] buffer = new byte[256];
			// 读数据
			while ((len = inputStream.read(buffer)) != -1) {

				System.out.println(new String(buffer, 0, len, "utf-8"));
			}
			inputStream.close();
			streamConnection.close();

		} catch (IOException e) {

			System.out.println("========== Socket Connection Error ==========");
			e.printStackTrace();

		} finally {

			System.out.println("========== Test Networks By Socket finish ==========");
		}
	}
}
