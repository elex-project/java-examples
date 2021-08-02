package kr.pe.elex.examples;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MockTest {
	@Test
	void mock() {
		Person jane = Mockito.mock(Person.class);
		jane.setName("Jane");

		assertNull(jane.getName());
	}

	@Test
	void mock2() {
		Person jane = Mockito.mock(Person.class);
		when(jane.getName()).thenReturn("Jane");
		when(jane.getAge()).thenReturn(22);

		assertEquals("Jane", jane.getName());
	}

	@Test
	void mock3() {
		ArrayList list = Mockito.mock(ArrayList.class);

		when(list.get(anyInt())).thenReturn("Apple");

		assertEquals("Apple", list.get(10));
	}

	@Test
	void mock4() {
		Person jane = Mockito.mock(Person.class);
		when(jane.getName()).thenReturn("Jane");
		when(jane.getAge()).thenReturn(22);

		assertEquals("Jane", jane.getName());
		assertEquals(22, jane.getAge());

		verify(jane, times(1)).getAge();

	}

	@Test
	void spy1() {
		Person jane = Mockito.spy(new Person("Jane",22,"jane@example.com"));
		when(jane.getName()).thenReturn("JANE");

		assertEquals("JANE", jane.getName());
		assertEquals(22, jane.getAge());

	}
}
