/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLinkExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class MarkdownParser {
	private static final Parser PARSER;
	private static final HtmlRenderer RENDERER;
	private static final AbstractYamlFrontMatterVisitor YAML_VISITOR;

	@Getter
	private final Node document;

	static {
		final MutableDataSet options = new MutableDataSet();
		options.set(
				Parser.EXTENSIONS,
				Arrays.asList(
						AbbreviationExtension.create(),
						AnchorLinkExtension.create(),
						AsideExtension.create(),
						AttributesExtension.create(),
						AutolinkExtension.create(),
						DefinitionExtension.create(),
						EnumeratedReferenceExtension.create(),
						FootnoteExtension.create(),
						StrikethroughSubscriptExtension.create(),
						InsExtension.create(),
						MediaTagsExtension.create(),
						SuperscriptExtension.create(),
						TablesExtension.create(),
						TypographicExtension.create(),
						YamlFrontMatterExtension.create(),
						YouTubeLinkExtension.create()
				));
		options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");
		options.set(HtmlRenderer.GENERATE_HEADER_ID, true);

		PARSER = Parser.builder(options).build();
		RENDERER = HtmlRenderer.builder(options).build();

		YAML_VISITOR = new AbstractYamlFrontMatterVisitor() {

		};
	}

	public MarkdownParser(final String md) {
		document = PARSER.parse(md);
	}

	public Map<String, List<String>> getYaml() {
		YAML_VISITOR.visit(document);
		return YAML_VISITOR.getData();
	}

	public String getHtml() {
		return RENDERER.render(document);
	}

}
