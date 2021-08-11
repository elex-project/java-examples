/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SocketServerSample {
	private List<Socket> clients = new ArrayList<>();
	private ServerSocket socket;

	public void open(int port) throws IOException {
		socket = new ServerSocket(port);

		while (!socket.isClosed()) {
			try {
				Socket client = socket.accept();
				clients.add(client);

			} catch (Throwable e) {
				log.error("Oops!", e);
			}
		}
	}

	public static void main(String... args){

	}
}
