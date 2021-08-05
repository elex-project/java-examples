/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonListTest {

	private static List<Person> persons;

	@BeforeAll
	static void beforeEach() {
		persons = Mockito.mock(List.class);
		persons.add(new Person("Charlie",11, "charlie@example.com"));
		persons.add(new Person("Steve",34, "steve@example.com"));
		persons.add(new Person("Jane",22, "jane@example.com"));
	}

	@Test
	void test() {
		System.out.println(persons.get(0));

		verify(persons).get(0);
	}
}
