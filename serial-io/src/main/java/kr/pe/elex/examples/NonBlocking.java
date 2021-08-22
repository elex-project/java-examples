/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.Console;
import com.fazecast.jSerialComm.SerialPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NonBlocking {
	public static void main(String... args) {
		SerialPort serialPort = SerialPort.getCommPort("/dev/ttyS0");
		serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
		serialPort.openPort();

		try {
			while (true) {
				while (serialPort.bytesAvailable() == 0) {
					Thread.sleep(20);
				}

				byte[] buffer = new byte[serialPort.bytesAvailable()];
				int numRead = serialPort.readBytes(buffer, buffer.length);
				Console.writeLine(buffer);
			}
		} catch (Throwable e) {
			log.error("Oops!", e);
		}

		serialPort.closePort();
	}
}
