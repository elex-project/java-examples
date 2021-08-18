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
public class CpuInfo {
	private static final int availableProcessors = OSInfo.get().getAvailableProcessors();
	private static long lastSystemTime = 0;
	private static long lastProcessCpuTime = 0;
	private double systemCpuLoad, processCpuLoad;
	private long processCpuTime;
	private CpuInfo() {
		com.sun.management.OperatingSystemMXBean mxBean =
				(com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		this.systemCpuLoad = mxBean.getSystemCpuLoad();
		this.processCpuLoad = mxBean.getProcessCpuLoad();
		this.processCpuTime = mxBean.getProcessCpuTime();
	}

	@NotNull
	@Contract(" -> new")
	public static CpuInfo get() {
		return new CpuInfo();
	}

	public static synchronized double getCpuUsage() {

		if (lastSystemTime == 0) {
			baselineCounters();
			return 0;
		}

		long systemTime = System.nanoTime();
		long processCpuTime = 0;

		processCpuTime = new CpuInfo().getProcessCpuTime();

		double cpuUsage = (double) (processCpuTime - lastProcessCpuTime) / (systemTime - lastSystemTime);

		lastSystemTime = systemTime;
		lastProcessCpuTime = processCpuTime;

		return cpuUsage / availableProcessors;
	}

	private static void baselineCounters() {
		lastSystemTime = System.nanoTime();
		lastProcessCpuTime = new CpuInfo().getProcessCpuTime();

	}

	public double getSystemCpuLoad() {
		return systemCpuLoad;
	}

	public double getProcessCpuLoad() {
		return processCpuLoad;
	}

	public long getProcessCpuTime() {
		return processCpuTime;
	}
}
