/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringWriter;

@Slf4j
class DOMParserTest {
	/*
	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
	<persons>
		<person age="11">Charlie</person>
		<person age="34">Steve</person>
	</persons>
	 */
	@Test
	void create() throws ParserConfigurationException, TransformerException {
		Document document = DOMParser.newDocument();

		Element rootElement = document.createElement("persons");
		document.appendChild(rootElement);

		Element charlie = document.createElement("person");
		charlie.setTextContent("Charlie");
		rootElement.appendChild(charlie);
		Attr attr = document.createAttribute("age");
		attr.setValue("11");
		charlie.setAttributeNode(attr);

		Element steve = document.createElement("person");
		steve.setTextContent("Steve");
		rootElement.appendChild(steve);
		attr = document.createAttribute("age");
		attr.setValue("34");
		steve.setAttributeNode(attr);

		StringWriter writer = new StringWriter();
		DOMParser.toString(document, writer);

		System.out.println(writer.toString());
	}

	@Test
	public void parse() throws ParserConfigurationException, IOException, SAXException {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><persons><person age=\"11\">Charlie</person><person age=\"34\">Steve</person></persons>";
		Document document = DOMParser.parse(xml);

		NodeList nodeList = document.getElementsByTagName("person");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getTextContent());
		}
	}
}
