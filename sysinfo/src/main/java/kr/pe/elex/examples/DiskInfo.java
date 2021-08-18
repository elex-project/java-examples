/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;

@Slf4j
public class DiskInfo {
	private final long totalSpace, freeSpace, usableSpace;

	private DiskInfo(@NotNull File root) {
		this.totalSpace = root.getTotalSpace();
		this.freeSpace = root.getFreeSpace();
		this.usableSpace = root.getUsableSpace();
	}

	@NotNull
	@Contract("_ -> new")
	public static DiskInfo get(@NotNull File root) {

		return new DiskInfo(root);
	}

	/**
	 * @return bytes
	 */
	public long getTotalSpace() {
		return totalSpace;
	}

	/**
	 * @return bytes
	 */
	public long getFreeSpace() {
		return freeSpace;
	}

	/**
	 * @return bytes
	 */
	public long getUsableSpace() {
		return usableSpace;
	}
}
