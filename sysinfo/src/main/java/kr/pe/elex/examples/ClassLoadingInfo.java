/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

@Slf4j
public class ClassLoadingInfo {
	private final ClassLoadingMXBean bean;

	private ClassLoadingInfo() {
		bean = ManagementFactory.getClassLoadingMXBean();

	}

	public static ClassLoadingInfo get() {
		return new ClassLoadingInfo();
	}

	public long getTotalLoadedClassCount() {
		return bean.getTotalLoadedClassCount();
	}

	public int getLoadedClassCount() {
		return bean.getLoadedClassCount();
	}
}
