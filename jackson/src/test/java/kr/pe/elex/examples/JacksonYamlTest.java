/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class JacksonYamlTest {


	@Test
	void write() throws IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.writeValue(new File("test.yaml"), new Person("Charlie", 11, true));
	}

	@Test
	void read() throws IOException {

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		Person person = mapper.readValue(new File("test.yaml"), Person.class);

		System.out.println(person);
	}
}
