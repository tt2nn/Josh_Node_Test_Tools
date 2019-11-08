package com.josh.node.test.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/**
 * 文件系统测试
 * 
 * @author lihaotian
 *
 */
public class TestFile {

	public static void main(String[] args) {

		System.out.println("========== Test File ==========");

		String fileName = "abc.txt";
		String fileURI = "/Phone/" + fileName;

		try {

			// 创建FileConnection , 目前file:///Phone/根目录是固定的
			FileConnection fileConnection = (FileConnection) Connector.open("file://" + fileURI);

			if (!fileConnection.exists()) {

				// 创建File
				fileConnection.create();
				System.out.println("========== Create File ==========");
			}

			if (fileConnection.exists()) {

				System.out.println("========== File Exists, Create File Success ==========");
			}

			// 写入File
			OutputStream outputStream = fileConnection.openOutputStream();
			String message = "Hello Josh";
			outputStream.write(message.getBytes());
			System.out.println("========== Write File ==========");

			outputStream.close();
			outputStream = null;
			fileConnection.close();
			fileConnection = null;

			// 读File
			fileConnection = (FileConnection) Connector.open("file://" + fileURI);

			if (!fileConnection.exists()) {
				System.out.println("========== Read File Error, File Not Exists ==========");
				fileConnection.close();
				return;
			}

			InputStream inputStream = fileConnection.openInputStream();
			byte[] buffer = new byte[256];
			int readLen = 0;

			System.out.println("========== Read File ==========");
			while ((readLen = inputStream.read(buffer)) != -1) {

				System.out.println(new String(buffer, 0, readLen));
			}

			inputStream.close();
			inputStream = null;
			fileConnection.close();
			fileConnection = null;

			// 删除文件
			fileConnection = (FileConnection) Connector.open("file://" + fileURI);
			if (fileConnection.exists()) {

				fileConnection.delete();
				System.out.println("========== Delete File ==========");
			}
			fileConnection.close();
			fileConnection = null;

			fileConnection = (FileConnection) Connector.open("file://" + fileURI);
			if (!fileConnection.exists()) {

				System.out.println("========== File Not Exists, Delete File Success ==========");
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			System.out.println("========== Test File Finish ==========");
		}
	}

}
