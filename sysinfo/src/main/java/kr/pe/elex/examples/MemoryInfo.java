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

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@Slf4j
public class MemoryInfo {
	private final long heapMax, heapUsed;
	private final long nonHeapMax, nonHeapUsed;
	private final long totalPhysicalMemory, freePhysicalMemory;
	private final long totalSwapMemory, freeSwapMemory;
	private MemoryInfo() {
		MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
		MemoryUsage usage = memory.getHeapMemoryUsage();
		this.heapMax = usage.getMax();
		this.heapUsed = usage.getUsed();
		usage = memory.getNonHeapMemoryUsage();
		this.nonHeapMax = usage.getMax();
		this.nonHeapUsed = usage.getUsed();

		com.sun.management.OperatingSystemMXBean osBean =
				(com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		this.totalPhysicalMemory = osBean.getTotalPhysicalMemorySize();
		this.freePhysicalMemory = osBean.getFreePhysicalMemorySize();
		this.totalSwapMemory = osBean.getTotalSwapSpaceSize();
		this.freeSwapMemory = osBean.getFreeSwapSpaceSize();
	}

	@NotNull
	@Contract(" -> new")
	public static MemoryInfo get() {
		return new MemoryInfo();
	}

	public long getHeapMax() {
		return heapMax;
	}

	public long getHeapUsed() {
		return heapUsed;
	}

	public long getNonHeapMax() {
		return nonHeapMax;
	}

	public long getNonHeapUsed() {
		return nonHeapUsed;
	}

	public long getTotalPhysicalMemory() {
		return totalPhysicalMemory;
	}

	public long getFreePhysicalMemory() {
		return freePhysicalMemory;
	}

	public long getTotalSwapMemory() {
		return totalSwapMemory;
	}

	public long getFreeSwapMemory() {
		return freeSwapMemory;
	}
}
