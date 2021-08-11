/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.http;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpSample {
	public static void main(String... args) throws IOException {
		requestGet();
	}

	public static void requestGet() throws IOException {
		URL url = new URL("https://www.google.com/");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.setDoOutput(false);
		connection.setConnectTimeout(25000);
		connection.setUseCaches(false);

		// 연결
		connection.connect();

		// 응답 처리
		System.out.println("STATUS: " + connection.getResponseCode());
		connection.getHeaderFields().forEach((key, list) -> {
			System.out.print(key + ": ");
			list.forEach(item -> {
				System.out.print(item + " ");
			});
			System.out.println();
		});
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			reader.lines().forEachOrdered(line -> {
				System.out.println(line);
			});
		}
		connection.disconnect();
	}

	public static void requestPost() throws IOException {
		URL url = new URL("https://www.google.com/");
		byte[] data = "Hello".getBytes(StandardCharsets.UTF_8);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "text/plain");
		connection.setFixedLengthStreamingMode(data.length);
		connection.setConnectTimeout(25000);
		connection.setUseCaches(false);

		// 연결
		connection.connect();

		// 요청 바디 전송
		OutputStream os = connection.getOutputStream();
		os.write(data);
		os.flush();

		// 응답 처리
		System.out.println("STATUS: " + connection.getResponseCode());
		connection.getHeaderFields().forEach((key, list) -> {
			System.out.print(key + ": ");
			list.forEach(item -> {
				System.out.print(item + " ");
			});
			System.out.println();
		});
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			reader.lines().forEachOrdered(line -> {
				System.out.println(line);
			});
		}
		connection.disconnect();
	}
}
