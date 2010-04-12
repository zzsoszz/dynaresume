/*******************************************************************************
 * Copyright (c) 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Chris Gross (schtoo@schtoo.com) - support for ILogger added
 *       (bug 49497 [RCP] JFace dependency on org.eclipse.core.runtime enlarges standalone JFace applications)
 *     Brad Reynolds - bug 164653
 *     Tom Schindl <tom.schindl@bestsolution.at> - bug 194587
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.logs;

import org.eclipse.core.databinding.pojo.bindable.logs.ILogger;
import org.eclipse.core.runtime.IStatus;

/**
 * Copy/Paste of JFace Databinding Policy class to have custom getDummyLog and
 * not use Policy of JFace Databinding.
 * 
 * @since 1.1
 */
public class Policy {

	private static ILogger log;

	/**
	 * Returns the dummy log to use if none has been set
	 */
	private static ILogger getDummyLog() {
		return new ILogger() {
			public void log(IStatus status) {
				StringBuffer buf = new StringBuffer();
				buf.append("Status ");
				int severity = status.getSeverity();
				switch (severity) {
				case IStatus.OK:
					buf.append("OK");
					break;
				case IStatus.CANCEL:
					buf.append("CANCEL");
					break;
				case IStatus.ERROR:
					buf.append("ERROR");
					break;
				case IStatus.INFO:
					buf.append("INFO");
					break;
				case IStatus.WARNING:
					buf.append("WARNING");
					break;
				}
				buf.append(": ");
				buf.append(status.getMessage());
				System.out.println(buf.toString());

				if (status.getException() != null) {
					status.getException().printStackTrace(System.err);
				}
			}
		};
	}

	/**
	 * Sets the logger used by JFace Data Binding to log errors.
	 * 
	 * @param logger
	 *            the logger to use, or <code>null</code> to use the default
	 *            logger
	 */
	public static synchronized void setLog(ILogger logger) {
		log = logger;
	}

	/**
	 * Returns the logger used by JFace Data Binding to log errors.
	 * <p>
	 * The default logger prints the status to <code>System.err</code>.
	 * </p>
	 * 
	 * @return the logger
	 */
	public static synchronized ILogger getLog() {
		if (log == null) {
			log = getDummyLog();
		}
		return log;
	}

}
