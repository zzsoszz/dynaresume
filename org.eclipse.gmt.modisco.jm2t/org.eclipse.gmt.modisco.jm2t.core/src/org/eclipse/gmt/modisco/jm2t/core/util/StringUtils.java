/*******************************************************************************
 * Copyright (c) 2010 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * String Utilities.
 * 
 */
public class StringUtils {

	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	/**
	 * Utility method to tokenize a string into an array.
	 * 
	 * @param str
	 *            a string to be parsed
	 * @param delim
	 *            the delimiters
	 * @return an array containing the tokenized string
	 */
	public static String[] tokenize(String str, String delim) {
		if (str == null)
			return EMPTY_STRING_ARRAY;

		List<String> list = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(str, delim);
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s != null && s.length() > 0)
				list.add(s.trim());
		}

		String[] s = new String[list.size()];
		list.toArray(s);
		return s;
	}
}
