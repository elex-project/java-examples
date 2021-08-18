/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.quartz.*;

public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println(jobKey);
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		System.out.println(jobDataMap.getString("myVar1"));
	}
}
