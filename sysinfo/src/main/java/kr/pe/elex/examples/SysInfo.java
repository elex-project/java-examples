/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.ThreadMXBean;

@Slf4j
class SysInfo {

	private SysInfo() {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		//threadMXBean.
		MemoryManagerMXBean memoryManagerMXBean = (MemoryManagerMXBean) ManagementFactory.getMemoryManagerMXBeans();
		//memoryManagerMXBean.
		CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
		//compilationMXBean.
	}
}
