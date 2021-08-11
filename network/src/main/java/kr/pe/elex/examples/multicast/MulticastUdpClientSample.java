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
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class MulticastUdpClientSample {
	public static void main(String... args) throws IOException {
		MulticastSocket socket = new MulticastSocket(18888);
		InetAddress group = InetAddress.getByName("224.0.0.1");
		socket.joinGroup(group);

		// 수신
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
		log.info("Rx: {}", received);

		socket.leaveGroup(group);
		socket.close();
	}
}
