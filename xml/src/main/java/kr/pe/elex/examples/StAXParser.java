/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class StAXParser {

	public static XMLStreamWriter create(OutputStream outputStream) throws XMLStreamException {

		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		return xmlOutputFactory.createXMLStreamWriter(outputStream, StandardCharsets.UTF_8.name());
	}


	public static void parse(String xml, Handler handler) throws XMLStreamException {
		parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);
	}

	public static void parse(InputStream xml, Handler handler) throws XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader eventReader =
				factory.createXMLEventReader(xml);
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();

			if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
				StartElement startElement = event.asStartElement();
				String qName = startElement.getName().getLocalPart();
				handler.startElement(qName, startElement);
			} else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
				EndElement endElement = event.asEndElement();
				String qName = endElement.getName().getLocalPart();
				handler.endElement(qName, endElement);
			} else if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
				Characters characters = event.asCharacters();
				handler.characters(characters.getData());
			}
		}
	}

	public interface Handler {
		public void startElement(String qName, StartElement element);

		public void endElement(String qName, EndElement element);

		public void characters(String data);
	}
}
