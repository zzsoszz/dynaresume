/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.internal.util;

/**
 * 
 * Utilities for String.
 * 
 */
public class StringUtils {

	public static final String[] EMPTY_STRING = new String[0];
	
	private static final String TRUE = "true";

	/**
	 * Returns true if value is null or empty and false otherwise.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.length() < 1;
	}

	/**
	 * Returns true if value is equals to 'true' and false otherwise.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isTrue(String value) {
		return TRUE.equals(value);
	}
}
