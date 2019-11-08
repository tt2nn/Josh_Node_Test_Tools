package com.josh.node.test.spi;

import org.joshvm.j2me.directUI.Display;
import org.joshvm.j2me.directUI.ImageBuffer;

import com.josh.node.test.spi.jedi.lcd.ILI9341_driver_DeviceAdaptor;

/**
 * 使用LCD_ESP测试SPI
 * 
 * @author lihaotian
 *
 */
public class TestSpiWithLCD_ESP {

	public static void main(String[] args) {

		System.out.println("========== Test SPI ==========");

		try {

			Display display = Display.getDisplay(new ILI9341_driver_DeviceAdaptor());

			ImageBuffer imageBuffer = new ImageBuffer(50, 50, ImageBuffer.TYPE_RGB565);
			display.clear(0x0f);
			Thread.sleep(2000);
			imageBuffer.setRGB(0x001f);
			display.showImageBuffer(0, 0, imageBuffer);

			System.out.println("========== SPI Draw Rect ==========");

			Thread.sleep(5000);

		} catch (Exception e) {

			System.out.println("========== Test SPI Error ==========");
			e.printStackTrace();
		}

		System.out.println("========== Test SPI Finish ==========");
	}

}
