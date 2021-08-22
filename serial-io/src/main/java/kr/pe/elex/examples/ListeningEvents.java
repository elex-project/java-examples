/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.Console;
import com.fazecast.jSerialComm.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListeningEvents {
	public static void main(String... args) {
		SerialPort serialPort = SerialPort.getCommPort("/dev/ttyS0");
		serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
		serialPort.openPort();

		serialPort.addDataListener(new SerialPortDataListener() {
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
					return;
				byte[] newData = new byte[serialPort.bytesAvailable()];
				int numRead = serialPort.readBytes(newData, newData.length);
				Console.writeLine(newData);
			}
		});

		serialPort.addDataListener(new SerialPortDataListener() {
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_WRITTEN;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN)
					Console.writeLine("All bytes were successfully transmitted!");
			}
		});

		serialPort.addDataListener(new SerialPortDataListener() {
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				byte[] newData = event.getReceivedData();
				Console.writeLine(newData);
			}
		});

		serialPort.addDataListener(new SerialPortPacketListener(){
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				byte[] newData = event.getReceivedData();
				Console.writeLine(newData);
			}

			@Override
			public int getPacketSize() {
				return 64;
			}
		});

		serialPort.addDataListener(new SerialPortMessageListener(){
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				byte[] newData = event.getReceivedData();
				Console.writeLine(newData);
			}

			@Override
			public byte[] getMessageDelimiter() {
				return new byte[]{0x7e};
			}

			@Override
			public boolean delimiterIndicatesEndOfMessage() {
				return false;
			}
		});

		serialPort.addDataListener(new SerialPortMessageListener(){
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				byte[] newData = event.getReceivedData();
				Console.writeLine(newData);
			}

			@Override
			public byte[] getMessageDelimiter() {
				return new byte[]{'\n'};
			}

			@Override
			public boolean delimiterIndicatesEndOfMessage() {
				return true;
			}
		});
	}
}
