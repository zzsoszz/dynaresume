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
package org.eclipse.gmt.modisco.jm2t.internal.ui.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Eclipse utility methods.
 */
public class EclipseUtil {
	/**
	 * EclipseUtil constructor comment.
	 */
	private EclipseUtil() {
		super();
	}

	/**
	 * Return a shell for the workbench.
	 * 
	 * @return org.eclipse.swt.widgets.Shell
	 */
	public static Shell getShell() {
		return getStandardDisplay().getActiveShell();
	}

	/**
	 * Returns the standard display to be used. The method first checks, if the
	 * thread calling this method has an associated display. If so, this display
	 * is returned. Otherwise the method returns the default display.
	 * 
	 * @return the display
	 */
	public static Display getStandardDisplay() {
		Display display = Display.getCurrent();
		if (display == null)
			display = Display.getDefault();

		return display;
	}

	/**
	 * Open a dialog window.
	 * 
	 * @param message
	 *            java.lang.String
	 */
	public static void openError(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Shell shell = getShell();
				MessageDialog.openError(shell, Messages.errorDialogTitle,
						message);
			}
		});
	}
	
	/**
	 * Open a dialog window.
	 *
	 * @param message java.lang.String
	 * @param status IStatus
	 */
	public static void openError(final String message, final IStatus status) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Shell shell = getShell();
				ErrorDialog.openError(shell, Messages.errorDialogTitle, message, status);
			}
		});
	}

	/**
	 * Open a dialog window.
	 *
	 * @param shell the shell
	 * @param message the message
	 */
	public static void openError(Shell shell, String message) {
		MessageDialog.openError(shell, Messages.errorDialogTitle, message);
	}

	/**
	 * Open a dialog window.
	 *
	 * @param shell a shell
	 * @param message a message
	 * @param status a status
	 */
	public static void openError(Shell shell, String message, IStatus status) {
		ErrorDialog.openError(shell, Messages.errorDialogTitle, message, status);
	}


}
