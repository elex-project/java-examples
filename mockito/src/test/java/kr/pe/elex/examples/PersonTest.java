package kr.pe.elex.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class PersonTest {

	private Person charlie, steve;

	@BeforeEach
	void beforeEach() {
		charlie = Mockito.mock(Person.class);
		when(charlie.getName()).thenReturn("Charlie");
		when(charlie.getAge()).thenReturn(11);

		doThrow(new IllegalArgumentException())
				.when(charlie).setName(eq("Brown"));

		steve = Mockito.spy(new Person("Steve",34,"steve@example.com"));
		when(steve.getName()).thenReturn("STEVE");
	}

	@Test
	void test1() {
		assertEquals("Charlie", charlie.getName());
		assertEquals(11, charlie.getAge());
	}

	@Test
	void test2() {
		assertThrows(IllegalArgumentException.class,
				() -> charlie.setName("Brown"));
	}

	@Test
	void test3() {
		assertEquals("STEVE", steve.getName());
		assertEquals(34, steve.getAge());
	}
}
