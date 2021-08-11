/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

@Slf4j
public class UdpClientSample {
	public static void main(String... args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		//socket.
		// 발신
		byte[] buf = "Hi~ there.".getBytes(StandardCharsets.UTF_8);
		DatagramPacket packet = new DatagramPacket(buf, buf.length,
				InetAddress.getByName("localhost"), 18888);
		socket.send(packet);

		// 수신
		buf = new byte[256];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
		log.info("Rx: {}", received);
	}
}
