/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.Console;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

class MustacheTest {

	@Test
	void test(){
		MustacheFactory factory = new DefaultMustacheFactory();
		Mustache mustache = factory
				//.compile("/sample.mustache"); // 리소스로부터 템플릿을 불러온다.
				.compile(new InputStreamReader(getClass().getResourceAsStream("/sample.mustache")),
						"sample");

		//Object context; // Object, List, Map 등 템플릿에 전달될 데이터
		Map<String, Object> context = new HashMap<>();
		context.put("person",new Person("Charlie", 14));

		StringWriter writer = new StringWriter();
		mustache.execute(writer, context);
		String result = writer.toString();

		Console.writeLine(result);
	}
}
