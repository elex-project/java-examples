/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.url;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class UrlSample {
	public static void readFromUrl() throws IOException {
		URL url = new URL("https://www.example.com/");
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream()))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				System.out.println(inputLine);
			}
			//in.close();
		}


	}
	public static void writeToUrl() throws IOException {
		URL url = new URL("https://www.example.com/");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.connect();

		try(OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			// write
			out.write("Hello~");

			// read
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				System.out.println(inputLine);
			}
		}

	}
}
