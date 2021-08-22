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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class Streaming {
	public static void main(String... args) {
		SerialPort serialPort = SerialPort.getCommPort("/dev/ttyS0");
		serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		serialPort.openPort();

		BufferedInputStream is = new BufferedInputStream(serialPort.getInputStream());
		BufferedOutputStream os = new BufferedOutputStream(serialPort.getOutputStream());
		try {
			while (true) {

				if (is.available()<=0){
					Thread.sleep(100);
				}

				byte[] buffer = new byte[is.available()];
				int numRead = is.read(buffer);
				Console.writeLine(buffer);
			}
		} catch (Throwable e) {
			log.error("Oops!", e);
		}

		serialPort.closePort();
	}
}
