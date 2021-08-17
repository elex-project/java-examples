/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class Thymeleaf {
	private final TemplateEngine templateEngine;

	@SneakyThrows
	public Thymeleaf() {
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(new MyTemplateResolver());
		templateEngine.setMessageResolver(new MyMessageResolver());
	}

	public String process(String template, Context context) throws IOException {

		StringWriter writer = new StringWriter();
		templateEngine.process(template, context, writer);

		String out = writer.toString();
		writer.close();
		return out;
	}

	private static class MyTemplateResolver extends ClassLoaderTemplateResolver {
		MyTemplateResolver() {
			super();
			this.setTemplateMode(TemplateMode.HTML);
			this.setSuffix(".html");
		}
	}

	private static class MyMessageResolver extends StandardMessageResolver {
		@SneakyThrows
		MyMessageResolver() {
			super();

			this.setDefaultMessages(properties(getClass()
					.getResourceAsStream("/messages.properties")));
		}

		@SneakyThrows
		@Override
		protected Map<String, String> resolveMessagesForTemplate(String template, ITemplateResource templateResource, Locale locale) {

			Map<String, String> map = propertiesToMap(getClass()
					.getResourceAsStream("/messages_" + locale.getLanguage() + ".properties"));
			return map;
		}
	}

	private static Map<String, String> propertiesToMap(InputStream is) throws IOException {
		Properties properties = properties(is);
		Map<String, String> map = new HashMap<>();
		for (String key : properties.stringPropertyNames()) {
			map.put(key, properties.getProperty(key));
		}
		return map;
	}

	private static Properties properties(InputStream is) throws IOException {
		Properties properties = new Properties();
		properties.load(new InputStreamReader(is, StandardCharsets.UTF_8));
		return properties;
	}
}
