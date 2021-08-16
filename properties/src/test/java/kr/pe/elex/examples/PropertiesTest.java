/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesTest {
	@Test
	void properties() throws IOException {
		Properties properties = new Properties();
		// 인풋스트림만 사용하면 인코딩이 꺠질 수 있으므로 리더를 사용해서 인코딩을 지정해서 불러 온다.
		properties.load(new InputStreamReader(getClass()
				.getResourceAsStream("/sample.properties"), StandardCharsets.UTF_8));

		String value1 = properties.getProperty("key1");
		System.out.println(value1);

		// setProperty(key, value);
		// properties.store(writer, comment);
	}

	@Test
	void xml_properties() throws IOException {
		Properties properties = new Properties();
		properties.loadFromXML(getClass()
				.getResourceAsStream("/sample.xml"));

		String value1 = properties.getProperty("key1");
		System.out.println(value1);

		// setProperty(key, value);
		//properties.storeToXML(os, comment);
	}
}
