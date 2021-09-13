/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@Slf4j
public class Converter {
	public static void main(String... args) throws UnsupportedAudioFileException, IOException {
		File inFile = new File("test_out.wav");
		File outFile = new File("test_out.au");

		AudioInputStream inputStream = AudioSystem.getAudioInputStream(inFile);
		AudioFormat outFormat = new AudioFormat(8000, 8, 1, true, false);

		if (AudioSystem.isConversionSupported(outFormat, inputStream.getFormat()) &&
				AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AU, inputStream)) {
			AudioInputStream lowResInputStream = AudioSystem
					.getAudioInputStream(outFormat, inputStream);

			AudioSystem.write(lowResInputStream, AudioFileFormat.Type.AU, outFile);
		}

	}
}
