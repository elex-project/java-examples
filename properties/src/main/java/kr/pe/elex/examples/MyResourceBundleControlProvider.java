/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import java.util.ResourceBundle;
import java.util.spi.ResourceBundleControlProvider;

public class MyResourceBundleControlProvider
		implements ResourceBundleControlProvider {
	static final ResourceBundle.Control XMLCONTROL =
			new MyResourceBundleControl();

	public ResourceBundle.Control getControl(String baseName) {
		System.out.println("Class: " + getClass().getName()+".getControl");
		System.out.println("    called for " + baseName);

		// Throws a NPE if baseName is null.
		if (baseName.startsWith("resources.Xml")) {
			System.out.println("    returns " + XMLCONTROL);
			return XMLCONTROL;
		}
		System.out.println("    returns null");
		System.out.println();
		return null;
	}
}
