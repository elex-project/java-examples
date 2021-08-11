/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Synchronizing {
	public static void main(String... args) {
		final Rabbit rabbit = new Rabbit();

		new Thread() {
			@Override
			public void run() {
				rabbit.hello();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				rabbit.count();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				rabbit.hello();
			}
		}.start();
	}

	static class Rabbit {
		public synchronized void count() {
			for (int i = 0; i < 100; i++) {
				System.out.println("Rabbit says " + i + ".");
			}
		}

		public synchronized void hello() {
			System.out.println("Rabbit says 'Hello'.");
		}
	}
}
