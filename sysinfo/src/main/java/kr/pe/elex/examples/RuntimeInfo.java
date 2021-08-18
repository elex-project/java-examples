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
import java.lang.management.RuntimeMXBean;

@Slf4j
public class RuntimeInfo {
	private String name, specName, specVendor, specVersion;
	private String vmName, vmVendor, vmVersion;
	private long startTime, upTime;
	private RuntimeInfo() {
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();

		this.name = runtime.getName();
		this.specName = runtime.getSpecName();
		this.specVendor = runtime.getSpecVendor();
		this.specVersion = runtime.getSpecVersion();

		this.vmName = runtime.getVmName();
		this.vmVendor = runtime.getVmVendor();
		this.vmVersion = runtime.getVmVersion();

		this.startTime = runtime.getStartTime();
		this.upTime = runtime.getUptime();
		try {
			/* Requires 10+ */
			//info.pid = runtime.getPid();
		} catch (Throwable e) {
			//info.pid = 0L;
		}
	}
	//private long pid; /* Requires 10+ */

	@NotNull
	@Contract(" -> new")
	public static RuntimeInfo get() {
		return new RuntimeInfo();
	}

	public String getName() {
		return name;
	}

	public String getSpecName() {
		return specName;
	}

	public String getSpecVendor() {
		return specVendor;
	}

	public String getSpecVersion() {
		return specVersion;
	}

	public String getVmName() {
		return vmName;
	}

	public String getVmVendor() {
		return vmVendor;
	}

	public String getVmVersion() {
		return vmVersion;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getUpTime() {
		return upTime;
	}
}
