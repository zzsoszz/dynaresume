/*******************************************************************************
 * Copyright (c) 2010 Martin Lippert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Martin Lippert                   initial implementation      
 *   Angelo ZERR                      manage springweaver into Jpa context
 *******************************************************************************/
package org.eclipse.equinox.weaving.springweaver.util;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 
 * Utilities for {@link Properties}.
 * 
 */
public class PropertiesUtils {

	/**
	 * Load properties file fileName and put it properties into properties
	 * instance.
	 * 
	 * @param properties
	 * @param fileName
	 * @param cl
	 */
	public static void loadProperties(final Properties properties,
			final String fileName, final ClassLoader cl) {

		// Loop for fileName properties file
		Enumeration<URL> urlProperties = null;
		try {
			urlProperties = cl == null ? ClassLoader
					.getSystemResources(fileName) : cl.getResources(fileName);
		} catch (IOException e) {
			return;
		}

		Properties fileProperties = new Properties();
		while (urlProperties.hasMoreElements()) {
			URL url = urlProperties.nextElement();
			try {
				if (url != null) {
					// Load properties file
					fileProperties.load(url.openStream());
					// merge properties file lodead into the properties.o
					properties.putAll(fileProperties);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
