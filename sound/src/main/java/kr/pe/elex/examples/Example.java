/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.elex_project.abraxas.Console;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;

@Slf4j
public class Example {
	public static void main(String... args) throws LineUnavailableException {
		Console.writeLine("# Audio file formats");
		for (AudioFileFormat.Type type : AudioSystem.getAudioFileTypes()) {
			Console.writeLine("{} / {}", type.toString(), type.getExtension());
		}

		Console.writeLine("# Audio mixers");
		for (Mixer.Info info : AudioSystem.getMixerInfo()) {
			Console.writeLine("{} / {} / {}", info.getName(), info.getDescription(), info.getVendor());
		}

		Console.writeLine("# Getting a Line from mixer");
		Mixer mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[1]);
		//mixer.sy
		for (Line.Info info : mixer.getSourceLineInfo()) {
			Console.writeLine("{}", info);
			if (mixer.isLineSupported(info)) {
				Line line = mixer.getLine(info);
				//line.open();
				//line.close();
			}
		}


		Console.writeLine("# Getting a Line from Audio System");
		AudioFormat audioFormat = new AudioFormat(441000,16,2, false,true);
		TargetDataLine line;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
		if (AudioSystem.isLineSupported(info)) {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
			line.close();
		}

		Console.writeLine("# Getting a Port from Audio System");
		if (AudioSystem.isLineSupported(Port.Info.SPEAKER)) {
			Port speakerPort = (Port) AudioSystem.getLine(Port.Info.SPEAKER);
		}
	}
}
