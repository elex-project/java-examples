/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class Sample {
	public static void main(String... args) {

	}


	public Stream<String> toStream(List<String> list) {
		return null == list ? Stream.empty() : list.stream();
	}


}
