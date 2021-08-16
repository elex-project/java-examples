/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
@Slf4j
public class ResourceBundleTest {
	@Test
	void resource_bundle_xml() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("language", new MyResourceBundleControl());

		System.out.println(bundle.getString("hello"));
		System.out.println(bundle.getString("only"));

	}

	@Test
	void resource_bundle_xml_jp() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("language", Locale.JAPANESE, new MyResourceBundleControl());
		System.out.println(bundle.getLocale());
		System.out.println(bundle.getString("hello"));
		System.out.println(bundle.getString("only"));


	}

	@Test
	void resource_bundle() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("lang");

		System.out.println(bundle.getString("hello"));
		System.out.println(bundle.getString("only"));
	}

	@Test
	void resource_bundle_jp() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("lang", Locale.JAPAN, new MyResourceBundleControl());
		log.info("Bundle locale: {}", bundle.getLocale());
		System.out.println(bundle.getString("hello"));
		System.out.println(bundle.getString("only"));
	}

	@Test
	void resource_bundle_fr() {
		Locale.setDefault(Locale.FRANCE);
		ResourceBundle bundle = ResourceBundle
				.getBundle("lang", Locale.FRENCH, new MyResourceBundleControl());
		log.info("Bundle locale: {}", bundle.getLocale());
		System.out.println(bundle.getString("hello"));
		System.out.println(bundle.getString("only"));
	}
}
