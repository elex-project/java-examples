/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class HttpD {
	public static void main(String... args) throws IOException {
		final ExecutorService threadPool = Executors.newCachedThreadPool();
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(18080), 0);
		httpServer.setExecutor(threadPool);

		httpServer.createContext("/", new HttpHandler() {
			@Override
			public void handle(final HttpExchange httpExchange) {
				try {
					httpExchange.getResponseHeaders().add("X-Datetime", LocalDateTime.now().toString());
					String message = "<html><body><h1>Hello, world!</h1></body></html>";
					//message += httpExchange.getRequestMethod();
					//BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
					//message += reader.readLine();
					httpExchange.sendResponseHeaders(200, message.getBytes().length);
					OutputStream os = httpExchange.getResponseBody();
					os.write(message.getBytes());
					os.flush();
					os.close();
				} catch (IOException e) {
				}

			}
		});
		httpServer.start();
	}

}
