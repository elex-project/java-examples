/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.*;

class SampleTest {

	@Test
	void create_stream() {
		Stream<String> stream1 = Stream.empty();

		List<String> list = new ArrayList<>();
		Stream<String> stream2 = list.stream();

		Set<String> set = new HashSet<>();
		Stream<String> stream3 = set.stream();

		Collection<String> collection = new HashSet<>();
		Stream<String> stream4 = collection.stream();

		Stream<String> stream5 = Stream.of("1", "2", "3");

		String[] strings = new String[]{"1", "2", "3"};
		Stream<String> stream6 = Stream.of(strings);
		stream6 = Arrays.stream(strings);

		Stream<String> stream7 = Stream.<String>builder()
				.add("1").add("2").add("3")
				.build();

		Stream<String> stream8 = Stream.generate(new Supplier<String>() {
			private int idx = 0;

			@Override
			public String get() {
				return strings[idx++];
			}
		}).limit(strings.length);

		Stream<String> stream9 = Stream.iterate(strings[0], new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return false;
			}
		}, new UnaryOperator<String>() {
			@Override
			public String apply(String s) {
				return null;
			}
		});
	}

	@Test
	void test_2() throws IOException {
		IntStream stream1 = IntStream.range(0, 10);
		IntStream stream2 = IntStream.rangeClosed(0, 10);

		LongStream stream3 = LongStream.range(0, 10);
		DoubleStream stream4 = DoubleStream.empty();

		IntStream stream5 = new Random().ints().limit(10);

		Stream<String> stream6 = "Hello\nWorld".lines();
		IntStream stream7 = "Hello".chars();
		Stream<String> stream8 = Pattern.compile(", ").splitAsStream("1, 2, 3");

		Path path = Paths.get("");
		Stream<String> stream9 = Files.lines(path, StandardCharsets.UTF_8);

	}


}
