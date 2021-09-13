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
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class SignalProcessing {

	public static void main(String... args)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			Console.writeLine("MIXER: {}", mixerInfo);
			for (Control control : mixer.getControls()){
				Console.writeLine("CONTROL: {}", control.toString());
			}
			for (Line.Info info : mixer.getSourceLineInfo()) {
				Console.writeLine("SOURCE LINE: {}" , info);
				Line line = mixer.getLine(info);
				for (Control control : line.getControls()){
					Console.writeLine("CONTROL: {}", control.toString());
				}
			}
			for (Line.Info info : mixer.getTargetLineInfo()) {
				Console.writeLine("TARGET LINE: {}" , info);
				Line line = mixer.getLine(info);
				for (Control control : line.getControls()){
					Console.writeLine("CONTROL: {}", control.toString());
				}
			}
		}

		/*AudioInputStream inputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(ClassLoader.
				getSystemResourceAsStream("alarm_gentle.wav")));
		SourceDataLine src = AudioSystem.getSourceDataLine(inputStream.getFormat());
		src.open();
		src.start();

		Mixer mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[1]);
		Console.writeLine(mixer.getLineInfo());
		Port line = (Port) mixer.getLine(Port.Info.LINE_IN);
		line.open();
		for (Control ctrl : line.getControls()) {
			Console.writeLine(ctrl);
		}

		FloatControl volumeCtrl = (FloatControl) mixer.getControl(FloatControl.Type.VOLUME);
		volumeCtrl.setValue(0);
		byte[] buffer = new byte[64];
		int numRead = 0;
		float dVol = 0.f;
		while ((numRead = inputStream.read(buffer, 0, buffer.length)) > 0) {
			log.debug("vol: {}", volumeCtrl.getValue());
			src.write(buffer, 0, numRead);
			if (volumeCtrl.getValue()<=volumeCtrl.getMinimum()) {
				dVol = 0.01f;
			} else if (volumeCtrl.getValue()>=volumeCtrl.getMaximum()){
				dVol = -0.01f;
			}
			volumeCtrl.setValue(volumeCtrl.getValue()+dVol);
		}

		inputStream.close();
		src.close();*/
	}

}
