/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.apache.commons.imaging.*;
import org.apache.commons.imaging.formats.png.PngConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommonsSample {
	public static void main(String... args) throws ImageWriteException, IOException, ImageReadException {
		readPNG();
	}

	public static void readPNG() throws IOException, ImageReadException, ImageWriteException {
		BufferedImage imageSource = Imaging.getBufferedImage(Objects
				.requireNonNull(Sample.class.getResourceAsStream("/java.png")));
		Image scaledImage = imageSource.getScaledInstance(16, 16, Image.SCALE_SMOOTH);

		BufferedImage newImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(scaledImage, 0, 0, null);

		final Map<String, Object> params = new HashMap<>();
		params.put(ImagingConstants.PARAM_KEY_COMPRESSION,
				TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED);

		Imaging.writeImage(newImage, new File("out/test-out.ico"), ImageFormats.TIFF, params);
		//PngConstants.
	}
}
