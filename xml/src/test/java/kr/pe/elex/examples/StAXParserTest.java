/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

class StAXParserTest {
	static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><persons><person age=\"11\">Charlie</person><person age=\"34\">Steve</person></persons>";

	@Test
	void create() throws XMLStreamException, IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		XMLStreamWriter xmlStreamWriter = StAXParser.create(outputStream);

		xmlStreamWriter.writeStartDocument();
		xmlStreamWriter.writeStartElement("persons");

		xmlStreamWriter.writeStartElement("person");
		xmlStreamWriter.writeAttribute("age", "11");
		xmlStreamWriter.writeCharacters("Charlie");
		xmlStreamWriter.writeEndElement();

		xmlStreamWriter.writeStartElement("person");
		xmlStreamWriter.writeAttribute("age", "34");
		xmlStreamWriter.writeCharacters("Steve");
		xmlStreamWriter.writeEndElement();

		xmlStreamWriter.writeEndElement();
		xmlStreamWriter.writeEndDocument();

		xmlStreamWriter.flush();
		xmlStreamWriter.close();

		System.out.println(new String(outputStream.toByteArray(), StandardCharsets.UTF_8));
		outputStream.close();

	}

	@Test
	void parse() throws XMLStreamException {
		StAXParser.parse(xml, new StAXParser.Handler() {
			private boolean mark = false;

			@Override
			public void startElement(String qName, StartElement element) {
				if (qName.equals("person")) {
					mark = true;
					System.out.println("age = " + element
							.getAttributeByName(new QName("age")).getValue());
				}
			}

			@Override
			public void endElement(String qName, EndElement element) {
				mark = false;
			}

			@Override
			public void characters(String data) {
				if (mark) {
					System.out.println("Name: " + data);
				}
			}
		});
	}
}
