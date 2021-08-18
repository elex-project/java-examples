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

@Slf4j
public class OSInfo {
	private String name, arch, version;
	private int availableProcessors;
	private double systemLoadAverage;
	private long totalPhysicalMemorySize, freePhysicalMemorySize;
	private long totalSwapSpaceSize, freeSwapSpaceSize;
	private long vmMemorySize;
	private double processCpuLoad;
	private long processCpuTime;
	private OSInfo() {
		com.sun.management.OperatingSystemMXBean bean =
				(com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		this.name = bean.getName();
		this.arch = bean.getArch();
		this.version = bean.getVersion();
		this.availableProcessors = bean.getAvailableProcessors();
		this.systemLoadAverage = bean.getSystemLoadAverage();
		this.processCpuLoad = bean.getProcessCpuLoad();
		this.processCpuTime = bean.getProcessCpuTime();
		this.totalPhysicalMemorySize = bean.getTotalPhysicalMemorySize();
		this.freePhysicalMemorySize = bean.getFreePhysicalMemorySize();
		this.totalSwapSpaceSize = bean.getTotalSwapSpaceSize();
		this.freeSwapSpaceSize = bean.getFreeSwapSpaceSize();
		this.vmMemorySize = bean.getCommittedVirtualMemorySize();
	}

	@NotNull
	@Contract(" -> new")
	public static OSInfo get() {
		return new OSInfo();
	}

	public String getName() {
		return name;
	}

	public String getArch() {
		return arch;
	}

	public String getVersion() {
		return version;
	}

	public int getAvailableProcessors() {
		return availableProcessors;
	}

	public double getSystemLoadAverage() {
		return systemLoadAverage;
	}

	public long getTotalPhysicalMemorySize() {
		return totalPhysicalMemorySize;
	}

	public long getFreePhysicalMemorySize() {
		return freePhysicalMemorySize;
	}

	public long getTotalSwapSpaceSize() {
		return totalSwapSpaceSize;
	}

	public long getFreeSwapSpaceSize() {
		return freeSwapSpaceSize;
	}

	public long getVmMemorySize() {
		return vmMemorySize;
	}

	public double getProcessCpuLoad() {
		return processCpuLoad;
	}

	public long getProcessCpuTime() {
		return processCpuTime;
	}
}
