/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


@Slf4j
public class Quartz {
	public static void main(String... args) throws SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();

		// 잡을 정의합니다.
		JobDetail job = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group1")
				.usingJobData("myVar1", "Hello")
				.build();

		// 트리거를 정의합니다.
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(30)
						.repeatForever())
				.build();

		// 잡과 트리거를 사용해서 스케쥴러에 전달합니다.
		scheduler.scheduleJob(job, trigger);
	}
}
