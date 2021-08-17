/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.Console;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class LocaleSampleTest {

	@Test
	void test(){
		Locale locale = Locale.KOREA;

		Console.writeLine("> Language: {}", locale.getLanguage());
		Console.writeLine("> Country: {}", locale.getCountry());

		Console.writeLine("> Display Name: {}", locale.getDisplayName());
		Console.writeLine("> Display Country: {}", locale.getDisplayCountry());
		Console.writeLine("> Display Language: {}", locale.getDisplayLanguage());
		Console.writeLine("> Display Script: {}", locale.getDisplayScript());
		Console.writeLine("> Display Variant: {}", locale.getDisplayVariant());

		Console.writeLine("> ISO3 Country: {}", locale.getISO3Country());
		Console.writeLine("> ISO3 Language: {}", locale.getISO3Language());

		Console.writeLine("> To Language Tag: {}", locale.toLanguageTag());
		Console.writeLine("> To String: {}", locale.toString());


	}
}
