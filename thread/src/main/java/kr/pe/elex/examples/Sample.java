/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Sample {
	public static void main(String... args) throws ExecutionException, InterruptedException {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		LocalDateTime startTime = LocalDateTime.of(2021, 12, 25, 0, 0);
		executor.scheduleAtFixedRate(new Runnable() {
			                             @Override
			                             public void run() {
											System.out.println("Hi~");
			                             }
		                             },
				Duration.between(LocalDateTime.now(), startTime).abs().toMillis(),
				Duration.ofDays(1).toMillis(),
				TimeUnit.MILLISECONDS);

	}
}
