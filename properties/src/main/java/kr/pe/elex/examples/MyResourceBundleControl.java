/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class MyResourceBundleControl extends ResourceBundle.Control {
	@Override
	public List<String> getFormats(String baseName) {
		if (baseName == null) {
			throw new NullPointerException();
		}
		return Collections.unmodifiableList(
				Arrays.asList("xml", "properties"));
	}

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale,
	                                String format,
	                                ClassLoader loader,
	                                boolean reload)
			throws IllegalAccessException,
			InstantiationException, IOException {
		if (baseName == null || locale == null
				|| format == null || loader == null) {
			throw new NullPointerException();
		}
		ResourceBundle bundle = null;
		if (format.equals("xml")) {
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, format);


			URL url = loader.getResource(resourceName);
			if (url != null) {
				URLConnection connection = url.openConnection();
				if (connection != null) {
					if (reload) {
						// disable caches if reloading
						connection.setUseCaches(false);
					}
					log.info("Bundle Name: {}", bundleName);
					log.info("Res Name: {}", resourceName);
					try (InputStream stream = connection.getInputStream()) {
						if (stream != null) {
							BufferedInputStream bis =
									new BufferedInputStream(stream);
							bundle = new XMLResourceBundle(bis);
						}
					}
				}
			}
		} else if (format.equals("properties")) {
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, format);


			URL url = loader.getResource(resourceName);
			if (url != null) {
				URLConnection connection = url.openConnection();
				if (connection != null) {
					if (reload) {
						// disable caches if reloading
						connection.setUseCaches(false);
					}
					log.info("Bundle Name: {}", bundleName);
					log.info("Res Name: {}", resourceName);
					//System.out.println("Res Name: " + resourceName);
					try (InputStream stream = connection.getInputStream()) {
						if (stream != null) {
							InputStreamReader reader =
									new InputStreamReader(new BufferedInputStream(stream), StandardCharsets.UTF_8);
							bundle = new PropertyResourceBundle(reader);
						}
					}
				}
			}
		}
		return bundle;
	}

	@Override
	public List<Locale> getCandidateLocales(String baseName, Locale locale) {

		List<Locale> locales = Arrays.asList(
				locale,
				new Locale(locale.getLanguage()),
				Locale.ROOT);
		log.info("locales: {} -> {}", locale, locales);
		return locales;
	}

	private static class XMLResourceBundle extends ResourceBundle {
		private final Properties props;

		XMLResourceBundle(InputStream stream) throws IOException {
			props = new Properties();
			props.loadFromXML(stream);
		}

		protected Object handleGetObject(String key) {
			if (key == null) {
				throw new NullPointerException();
			}
			return props.getProperty(key);
		}

		public Enumeration<String> getKeys() {
			Set<String> handleKeys = props.stringPropertyNames();

			return Collections.enumeration(handleKeys);
		}

		@NotNull
		@Override
		protected Set<String> handleKeySet() {
			return props.stringPropertyNames();
		}
	}
}
