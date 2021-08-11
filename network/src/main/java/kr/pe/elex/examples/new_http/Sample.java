/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.new_http;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class Sample {
	public static void main(String... args) throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.build();

		// GET
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create("https://www.google.com/"))
				.header("X-Header", "Something")
				.build();
		HttpResponse<String> response = httpClient
				.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.body());

		// POST
		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString("Hello"))
				.uri(URI.create("https://www.examples.com/"))
				.build();
		response = httpClient
				.send(request, HttpResponse.BodyHandlers.ofString());
	}
}
