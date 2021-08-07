/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.IOz;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class MarkdownParserTest {

	private static String md;

	@BeforeAll
	private static void beforeAll() throws IOException {
		md = IOz.readStringFrom(MarkdownParserTest.class
				.getResourceAsStream("/sample.md"));
	}

	@Test
	void parse() throws IOException {
		MarkdownParser markdownParser = new MarkdownParser(md);
		String html = markdownParser.getHtml();
		System.out.println(html);

		Map<String, List<String>> yaml = markdownParser.getYaml();
		for (String key : yaml.keySet()) {
			System.out.println(key + " : " + yaml.get(key).get(0));
		}
	}
}
