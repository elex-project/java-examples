/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class JacksonXmlTest {


	@Test
	void write() throws IOException {
		XmlMapper mapper = XmlMapper.builder()
				.defaultUseWrapper(false)
				.enable(SerializationFeature.INDENT_OUTPUT)
				.build();
		mapper
				.writeValue(new File("test.xml"), new Person("Charlie", 11, true));
	}

	@Test
	void read() throws IOException {

		ObjectMapper mapper = new XmlMapper();
		Person person = mapper.readValue(new File("test.xml"), Person.class);

		System.out.println(person);
	}
}
