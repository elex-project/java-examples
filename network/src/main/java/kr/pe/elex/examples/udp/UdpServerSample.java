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
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
public class UdpServerSample {
	public static void main(String... args) throws IOException {
		DatagramSocket socket = new DatagramSocket(18888);

		// 수신
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);

		log.info("Rx: {}", new String(Arrays.copyOfRange(buf,packet.getOffset(),packet.getLength())));

		// 발신
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		byte[] resp = "Hello~!".getBytes(StandardCharsets.UTF_8);
		packet = new DatagramPacket(resp, resp.length, address, port);
		socket.send(packet);


	}
}
