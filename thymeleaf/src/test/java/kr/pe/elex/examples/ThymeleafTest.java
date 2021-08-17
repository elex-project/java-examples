/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.Console;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.thymeleaf.context.Context;

import java.io.IOException;

@Slf4j
class ThymeleafTest {

	@Test
	void test() throws IOException {
		Context context = new Context();
		//context.setLocale(Locale.ENGLISH);
		context.setVariable("person", new Person("Charlie", 14));
		context.setVariable("html", "<strong>Hahaha</strong>");

		Thymeleaf thymeleaf = new Thymeleaf();
		String out = thymeleaf.process("sample.html", context);

		Console.writeLine(out);
	}
}
