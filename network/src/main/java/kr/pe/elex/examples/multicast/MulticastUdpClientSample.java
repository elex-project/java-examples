/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.multicast;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

@Slf4j
public class MulticastUdpClientSample {
	public static void main(String... args) throws IOException {
		new Thread() {
			@Override
			public void run() {
				try {
					client("ONE");
				} catch (IOException e) {
					log.error("Oops", e);
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					client("TWO");
				} catch (IOException e) {
					log.error("Oops", e);
				}
			}
		}.start();
	}

	public static void client(String name) throws IOException {
		MulticastSocket socket = new MulticastSocket(18888);
		InetAddress group = InetAddress.getByName("224.0.0.1");
		socket.joinGroup(group);

		for (int i = 0; i < 10; i++) {
			// 수신
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			String received = new String(packet.getData(), 0, packet.getLength());
			log.info("{} Rx: {}", name, received);
		}

		socket.leaveGroup(group);
		socket.close();
	}
}
