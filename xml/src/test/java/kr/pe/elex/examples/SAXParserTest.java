/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SAXParserTest {
	static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><persons><person age=\"11\">Charlie</person><person age=\"34\">Steve</person></persons>";

	@Test
	void parse() throws ParserConfigurationException, IOException, SAXException {
		SAXParser.parse(xml,new DefaultHandler(){
			private boolean mark = false;
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				if (qName.equals("person")) {
					mark = true;
					System.out.println("age= " + attributes.getValue("age"));
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				mark = false;
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				if (mark){
					System.out.println("Name: " + new String(ch, start, length));
				}
			}
		});
	}
}
