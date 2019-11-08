package com.josh.node.test.uart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

/**
 * 测试串口
 * 
 * @author lihaotian
 *
 */
public class TestUart {

	public static void main(String[] args) {

		System.out.println("========== Test Uart ==========");

		String com = "COM1";
		int baudrate = 9600;
		String host = "comm:" + com + ";baudrate=" + baudrate;

		try {

			StreamConnection streamConnection = (StreamConnection) Connector.open(host);

			System.out.println("========== Uart Connect Success ==========");

			InputStream inputStream = streamConnection.openInputStream();
			OutputStream outputStream = streamConnection.openOutputStream();

			int len = 0;
			byte[] buffer = new byte[256];
			StringBuffer stringBuffer = null;

			// 阻塞读，收到数据，发送同样的数据
			while ((len = inputStream.read(buffer)) != -1) {

				stringBuffer = new StringBuffer();
				for (int i = 0; i < len; i++) {

					stringBuffer.append(Integer.toHexString(buffer[i] & 0xFF) + " ");
				}

				System.out.println("========== Uart Receive == " + stringBuffer.toString());

				outputStream.write(buffer, 0, len);
				System.out.println("========== Uart Send Data ==========");
			}

		} catch (IOException e) {

			System.out.println("========== Uart Connection Error ==========");
			e.printStackTrace();

		} finally {

			System.out.println("========== Uart Disconnected ==========");
		}
	}

}
