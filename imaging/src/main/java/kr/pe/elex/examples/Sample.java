/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Sample {
	public static void main(String... args) throws IOException {
		formats();
		//readPNG();
	}

	public static void readPNG() throws IOException {
		BufferedImage imageSource = ImageIO.read(Objects
				.requireNonNull(Sample.class.getResourceAsStream("/java.png")));
		Image scaledImage = imageSource.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

		BufferedImage newImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = newImage.createGraphics();
		boolean r = g.drawImage(scaledImage, 0, 0, null);

		System.out.println("? " + r);
		ImageIO.write(newImage, "JPG", new File("out/test-out.jpg"));
		//ImageIO.write(imageSource, "JPG", new File("test-src-out.jpg"));
	}

	public static BufferedImage convert(final Image image, final int imageTYpe) {
		final BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				imageTYpe);

		final Graphics2D g = newImage.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}

	public static void formats(){
		System.out.println("Writer");
		for (String item : ImageIO.getWriterFormatNames()) {
			System.out.println(item);
		}
		System.out.println("Reader");
		for (String item : ImageIO.getReaderFormatNames()) {
			System.out.println(item);
		}
	}
}
