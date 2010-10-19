package org.eclipse.gmt.modisco.jm2t.internal.core.util;

import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;

public class Util {

	public final static String UTF_8 = "UTF-8"; //$NON-NLS-1$
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator"); //$NON-NLS-1$

	/**
	 * Returns the line separator found in the given text. If it is null, or not
	 * found return the line delimitor for the given project. If the project is
	 * null, returns the line separator for the workspace. If still null, return
	 * the system line separator.
	 */
	public static String getLineSeparator(String text, IJM2TProject project) {
		return LINE_SEPARATOR;
	}

}
