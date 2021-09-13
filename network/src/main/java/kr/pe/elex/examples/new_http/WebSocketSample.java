/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.new_http;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@Slf4j
public class WebSocketSample {
	private static class WebSocketListener implements WebSocket.Listener {
		private StringBuilder sb;

		WebSocketListener() {
			sb = new StringBuilder();
		}

		@Override
		public void onOpen(WebSocket webSocket) {
			log.debug("Open!");
			//webSocket.request(1);
			WebSocket.Listener.super.onOpen(webSocket);
		}

		@Override
		public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
			sb.append(data);
			webSocket.request(1);

			if (last) {
				String message = sb.toString();

				log.debug("Rx: {}", message);

				sb = new StringBuilder();

				return CompletableFuture.completedStage(null);
			}
			//return WebSocket.Listener.super.onText(webSocket, data, last);
			return null;
		}

		@Override
		public void onError(WebSocket webSocket, Throwable error) {
			log.error("Error!", error);
			WebSocket.Listener.super.onError(webSocket, error);
		}
	}

	public static void main(String... args) throws ExecutionException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
		CompletableFuture<WebSocket> completableFuture = httpClient.newWebSocketBuilder()
				.buildAsync(URI.create("ws://localhost:8080/websocket"), new WebSocketListener());

		WebSocket webSocket = completableFuture.get();

		while (true) {

			Thread.sleep(1500);
			webSocket.sendText("Hello", false);
			webSocket.sendText(" World", true);
		}
	}


}
