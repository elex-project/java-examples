/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class Playback {

	public static void main(String... args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		playback();

		while (true) {

		}
	}

	private static void playback() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(ClassLoader.
				getSystemResourceAsStream("alarm_gentle.wav")));

		SourceDataLine line = AudioSystem.getSourceDataLine(inputStream.getFormat());
		line.open();
		line.start();
		line.addLineListener(event -> {
			if (event.getType().equals(LineEvent.Type.OPEN)) {

			} else if (event.getType().equals(LineEvent.Type.START)) {

			} else if (event.getType().equals(LineEvent.Type.STOP)) {

			} else if (event.getType().equals(LineEvent.Type.CLOSE)) {

			}
		});
		byte[] buffer = new byte[32];
		int numRead = 0;
		while ((numRead = inputStream.read(buffer, 0, buffer.length)) > 0) {
			log.debug("read: {}", numRead);
			line.write(buffer, 0, numRead);
		}

		inputStream.close();
		line.close();
	}

	private static void playbackClip() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(Objects.requireNonNull(ClassLoader.
				getSystemResourceAsStream("alarm_gentle.wav"))));
		//clip.loop(3);
		clip.start();

		//clip.stop();
	}
}
