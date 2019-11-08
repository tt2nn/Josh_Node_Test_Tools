package com.josh.node.test.gpio;

import org.joshvm.j2me.dio.DeviceManager;
import org.joshvm.j2me.dio.gpio.GPIOPin;
import org.joshvm.j2me.dio.gpio.GPIOPinConfig;
import org.joshvm.j2me.dio.gpio.PinEvent;
import org.joshvm.j2me.dio.gpio.PinListener;

/**
 * 测试GPIO
 * 
 * @author lihaotian
 *
 */
public class TestGpio {

	public static void main(String[] args) {

		System.out.println("========== Test GPIO ==========");

		TestLight();

		System.out.println("========== Test GPIO Finish ==========");
	}

	/**
	 * 用灯测试GPIO In
	 */
	private static void TestLight() {

		System.out.println("========== Test GPIO With Light ==========");

		try {

			GPIOPin gpioPin = openGpioLight(22);
			System.out.println("========== Open GPIO ==========");

			Thread.sleep(2000);

			gpioIn(gpioPin, true);
			System.out.println("========== GPIO In True ==========");

			Thread.sleep(2000);

			gpioIn(gpioPin, false);
			System.out.println("========== GPIO In False ==========");

		} catch (Exception e) {

			System.out.println("========== Test GPIO Error ==========");
			e.printStackTrace();
		}

	}

	/**
	 * 用按键测试GPIO Out
	 */
	private static void TestKey() {

		System.out.println("========== Test GPIO With Key ==========");

		try {

			GPIOPin gpioPin = openGpioKey(22);
			System.out.println("========== Open GPIO ==========");

			gpioOut(gpioPin, new PinListener() {

				public void valueChanged(PinEvent arg0) {

					System.out.println("========== GPIO Out == " + arg0.getValue());
				}
			});

		} catch (Exception e) {

			System.out.println("========== Test GPIO Error ==========");
			e.printStackTrace();
		}
	}

	/**
	 * Open灯GPIO
	 * 
	 * @param gpioNo
	 * @return
	 * @throws Exception
	 */
	private static GPIOPin openGpioLight(int gpioNo) throws Exception {

		GPIOPinConfig cfgOutput22 = new GPIOPinConfig(GPIOPinConfig.UNASSIGNED, gpioNo, GPIOPinConfig.DIR_OUTPUT_ONLY,
				GPIOPinConfig.MODE_OUTPUT_PUSH_PULL, GPIOPinConfig.TRIGGER_NONE, false);

		return (GPIOPin) DeviceManager.open(cfgOutput22, DeviceManager.EXCLUSIVE);
	}

	/**
	 * Open按键GPIO
	 * 
	 * @param gpioNo
	 * @return
	 * @throws Exception
	 */
	private static GPIOPin openGpioKey(int gpioNo) throws Exception {

		GPIOPinConfig cfg0 = new GPIOPinConfig(GPIOPinConfig.UNASSIGNED, gpioNo, // SPI0_1_DI
				GPIOPinConfig.DIR_INPUT_ONLY, GPIOPinConfig.MODE_INPUT_PULL_UP, GPIOPinConfig.TRIGGER_BOTH_LEVELS,
				true);

		return (GPIOPin) DeviceManager.open(cfg0, DeviceManager.EXCLUSIVE);
	}

	/**
	 * 输入
	 * 
	 * @param gpioPin
	 * @param value
	 * @throws Exception
	 */
	private static void gpioIn(GPIOPin gpioPin, boolean value) throws Exception {

		gpioPin.setValue(value);
	}

	/**
	 * 输出
	 * 
	 * @param gpioPin
	 * @param pinListener
	 * @throws Exception
	 */
	private static void gpioOut(GPIOPin gpioPin, PinListener pinListener) throws Exception {

		gpioPin.setInputListener(pinListener);
	}

}
