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
import java.nio.charset.StandardCharsets;

@Slf4j
public class MulticastUdpServerSample {
	public static void main(String... args) throws IOException {
		MulticastSocket socket = new MulticastSocket();

		/*
		IPv4 multicast addresses are defined by the leading address bits of 1110,
		originating from the classful network design of the early Internet when this group of addresses
		was designated as Class D. The Classless Inter-Domain Routing (CIDR) prefix of this group is 224.0.0.0/4.
		The group includes the addresses from 224.0.0.0 to 239.255.255.255.

		A multicast group is specified by a class D IP address and by a standard UDP port number.
		Class D IP addresses are in the range 224.0.0.0 to 239.255.255.255, inclusive.
		The address 224.0.0.0 is reserved and should not be used.

		 */
		for (int i = 0; i < 20; i++) {
			// 발신
			InetAddress group = InetAddress.getByName("224.0.0.1");
			byte[] data = ("Hello~! " + i).getBytes(StandardCharsets.UTF_8);
			DatagramPacket packet = new DatagramPacket(data, data.length, group, 18888);
			socket.send(packet);
			log.info("Tx: {}", new String(packet.getData()));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error("Oops~!", e);
			}
		}

		socket.disconnect();
	}
}
