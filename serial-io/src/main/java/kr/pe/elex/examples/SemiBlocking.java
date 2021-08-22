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
public class SemiBlocking {
	public static void main(String... args) {
		SerialPort serialPort = SerialPort.getCommPort("/dev/ttyS0");
		serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		serialPort.openPort();

		try {
			while (true) {

				byte[] buffer = new byte[512];
				int numRead = serialPort.readBytes(buffer, buffer.length);
				Console.writeLine(buffer);
			}
		} catch (Throwable e) {
			log.error("Oops!", e);
		}

		serialPort.closePort();
	}
}
