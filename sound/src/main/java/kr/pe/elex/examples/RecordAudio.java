/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RecordAudio {
	static boolean isRunning = true;

	public static void main(String... args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

		TargetDataLine line;
		AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
		if (AudioSystem.isLineSupported(info)) {
			line = AudioSystem.getTargetDataLine(audioFormat);
			log.debug("{}", line.getLineInfo());
			line.open(audioFormat);
			line.start();

			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.schedule(() -> isRunning = false, 5, TimeUnit.SECONDS);

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] buffer = new byte[32];

			while (isRunning) {
				int numRead = line.read(buffer, 0, buffer.length);
				os.write(buffer, 0, numRead);

				log.debug("{}", line.getLevel());
			}
			log.debug("end of loop");

			File file = new File("test_out.wav");
			AudioInputStream is = new AudioInputStream(new ByteArrayInputStream(os.toByteArray()),
					audioFormat, os.size()/2/2);

			//AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(new File(""));

			AudioSystem.write(is, AudioFileFormat.Type.WAVE, file);

			os.close();
			line.close();
			service.shutdown();
		}
	}
}
