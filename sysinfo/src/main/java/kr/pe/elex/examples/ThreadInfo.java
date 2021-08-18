/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.sun.management.ThreadMXBean;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.management.ManagementFactory;

@Slf4j
public class ThreadInfo {
	private final ThreadMXBean bean;

	private ThreadInfo() {
		bean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
	}

	@Contract(" -> new")
	public static @NotNull ThreadInfo get() {
		return new ThreadInfo();
	}

	public long[] getThreadIds() {
		return bean.getAllThreadIds();
	}

	public long getThreadCpuTime(long id) {
		return bean.getThreadCpuTime(id);
	}

	public long getThreadUserTime(long id) {
		return bean.getThreadUserTime(id);
	}

	public int getThreadCount() {
		return bean.getThreadCount();
	}

	public long getAllocatedBytes(long id) {
		return bean.getThreadAllocatedBytes(id);
	}
}
