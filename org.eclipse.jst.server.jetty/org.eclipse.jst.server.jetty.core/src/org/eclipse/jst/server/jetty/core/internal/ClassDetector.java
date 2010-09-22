/*******************************************************************************
 * Copyright (c) 2010 Angelo Zerr and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com> - Initial API and implementation 
 *******************************************************************************/
package org.eclipse.jst.server.jetty.core.internal;
/**
 * Utility class to check for the existence of a class given as an
 * argument. 
 */
public class ClassDetector {
	public static void main(String[] args) {
		if (args == null || args.length != 1) {
			System.out.println("Usage: ClassDetector [className]");
			return;
		}
		
		try {
			Class.forName(args[0]);
			System.out.println("true");
		} catch (Exception e) {
			System.out.println("false");
		}
	}
}