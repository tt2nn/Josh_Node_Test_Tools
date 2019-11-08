package com.josh.node.test.iic;

import java.io.IOException;

import org.joshvm.j2me.dio.DeviceManager;
import org.joshvm.j2me.dio.i2cbus.I2CDevice;
import org.joshvm.j2me.dio.i2cbus.I2CDeviceConfig;
import org.joshvm.util.ByteBuffer;

/**
 * 使用IIC三轴加速度传感器测试
 * 
 * @author lihaotian
 *
 */
public class TestIICWithSensor {

	public static void main(String[] args) {

		// BMA250
		System.out.println("========== Test IIC ==========");

		I2CDeviceConfig cfg = new I2CDeviceConfig.Builder().setAddress(0x18, I2CDeviceConfig.ADDR_SIZE_7).build();

		try {

			I2CDevice iic = (I2CDevice) DeviceManager.open(cfg, DeviceManager.EXCLUSIVE);
			ByteBuffer dst = ByteBuffer.allocateDirect(1);
			iic.read(0x00, 1, dst);

			System.out.println("========== IIC ID == " + dst.array()[0]);

			dst = ByteBuffer.allocateDirect(1);
			iic.read(0x0F, 1, dst);
			byte g_range = dst.array()[0];
			switch (g_range) {
			case 0x03:
				g_range = 2;
				break;
			case 0x05:
				g_range = 5;
				break;
			case 0x08:
				g_range = 8;
				break;
			case 0x0c:
				g_range = 16;
				break;
			}
			System.out.println("g-range:" + g_range);

			dst = ByteBuffer.allocateDirect(7);
			while (true) {

				try {
					iic.read(0x02, 1, dst);
					byte[] data = dst.array();
					dst.position(0);
					System.out.print("[" + data[0] + "]");
					System.out.print("[" + data[1] + "]");
					System.out.print("[" + data[2] + "]");
					System.out.print("[" + data[3] + "]");
					System.out.print("[" + data[4] + "]");
					System.out.println("[" + data[5] + "]");

					System.out.print("acc_x:" + (getData(data[0], data[1]) / 512.0 * g_range) + "g");
					System.out.print(" acc_y:" + (getData(data[2], data[3]) / 512.0 * g_range) + "g");
					System.out.println(" acc_z:" + (getData(data[4], data[5]) / 512.0 * g_range) + "g");
					System.out.println("temp:" + ((data[6] * 0.5) + 24));

				} catch (IOException e) {
					e.printStackTrace();

				}

				Thread.sleep(1000);
			}

		} catch (Exception e) {

			System.out.println("========== Test IIC Error ==========");
			e.printStackTrace();
		}

		System.out.println("========== Test IIC Finish ==========");
	}

	private static int getData(byte d1, byte d2) {
		int num = ((d1 >> 6) & 0x03) | (d2 << 2);
		return num;
	}

}
