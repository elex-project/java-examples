/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class SAXParser {
	public static void parse(String xml, DefaultHandler handler)
			throws ParserConfigurationException, SAXException, IOException {
		parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);
	}

	public static void parse(InputStream xml, DefaultHandler handler)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(xml, handler);
	}
}
