/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Sample {
	public static void main(String... args) throws IOException, TranscoderException {
		toBufferedImage();
	}

	public static void toPNG() throws FileNotFoundException, TranscoderException {
		PNGTranscoder transcoder = new PNGTranscoder();
		transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, 64f);
		transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, 64f);

		TranscoderInput input = new TranscoderInput(Sample.class
				.getResourceAsStream("/java.svg"));
		TranscoderOutput output = new TranscoderOutput(
				new FileOutputStream(new File("out/test_out.png")));
		transcoder.transcode(input, output);

	}

	public static void toBufferedImage() throws IOException, TranscoderException {
		PNGTranscoder transcoder = new PNGTranscoder();
		transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, 64f);
		transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, 64f);

		TranscoderInput input = new TranscoderInput(Sample.class
				.getResourceAsStream("/java.svg"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		TranscoderOutput output = new TranscoderOutput(os);
		transcoder.transcode(input, output);

		BufferedImage out = ImageIO.read(new ByteArrayInputStream(os.toByteArray()));
		ImageIO.write(out, "png", new File("out/test-buffered-image.png"));
	}
}
