/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.Exez;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SysInfoUtils {

	private SysInfoUtils() {
	}

	@NotNull
	public static Map<String, String> getOSInfo() {
		HashMap<String, String> map = new HashMap<>();
		try {
			List<String> result = Exez.exec("cat /etc/*-release");
			for (String line : result) {
				String[] parted = line.split("=");
				map.put(parted[0], parted[1]);
			}

		} catch (IOException | InterruptedException e) {
			log.error("Oops!", e);
		}

		return map;
	}
}
