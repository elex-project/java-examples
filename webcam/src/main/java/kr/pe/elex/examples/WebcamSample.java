/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebcamSample {
	public static void main(String... args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame jFrame = new JFrame("Web Cam");
				jFrame.setSize(800, 600);
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				jFrame.setContentPane(new MyPanel());

				jFrame.setVisible(true);
			}
		});
	}

	private static class MyPanel extends JPanel {
		private Webcam webcam;

		MyPanel() {
			super();
			init();
		}

		private void init() {
			setLayout(new BorderLayout());

			webcam = Webcam.getDefault();
			Dimension resolution = WebcamResolution.QVGA.getSize();
			webcam.setViewSize(resolution);
			webcam.open();

			WebcamPanel panel = new WebcamPanel(webcam);
			panel.setFPSDisplayed(true);
			panel.setAntialiasingEnabled(true);
			panel.setSize(resolution);

			JButton btnCapture = new JButton("Capture");
			btnCapture.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						BufferedImage image = webcam.getImage();
						ImageIO.write(image, "jpg", new File("out/capture.jpg"));
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			});
			JButton btnResolution = new JButton("Resolutions");
			btnResolution.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (Dimension item : webcam.getDevice().getResolutions()) {
						System.out.println(item);
					}
				}
			});

			JToolBar jToolBar = new JToolBar();
			jToolBar.add(btnCapture);
			jToolBar.add(btnResolution);

			add(panel, BorderLayout.CENTER);
			add(jToolBar, BorderLayout.NORTH);
		}
	}
}
