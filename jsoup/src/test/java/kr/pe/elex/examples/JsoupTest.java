/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsoupTest {
	@Test
	void read_url() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		//doc.body().
	}
}
