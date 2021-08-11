/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

@Slf4j
public class SocketClientSample {
	public static void main(String... args) throws IOException {
		Socket socket = new Socket("localhost", 18188);
		// socket.getInputStream();
		// socket.getOutputStream();

		socket.close();
	}
}
