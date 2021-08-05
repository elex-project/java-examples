/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class JacksonCsvTest {
	private static List<Person> list;

	@BeforeAll
	private static void beforeAll() {
		list = new ArrayList<>();
		list.add(new Person("Charlie", 11, true));
		list.add(new Person("Steve", 34, true));
		list.add(new Person("Jane", 22, false));
	}

	@Test
	void writeToCsv() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper
				.schemaFor(Person.class)
				.withHeader();

		//String csvOut = mapper.writer(schema).writeValueAsString(list);
		//System.out.println(csvOut);

		mapper.writer(schema)
				.writeValue(new File("test.csv"), list);
	}

	@Test
	void readFromCsv() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper
				.schemaFor(Person.class)
				.withHeader();

		MappingIterator<Person> iterator = mapper.readerFor(Person.class)
				.with(schema)
				.readValues(new File("test.csv"));
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}

	}
}
