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
package org.eclipse.jst.server.jetty.ui.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	public static String wizardTitle;
	public static String wizardDescription;
	public static String runtimeName;
	public static String browse;
	public static String selectInstallDir;
	public static String installDir;
	public static String install;
	public static String installDialogTitle;
	public static String installedJRE;
	public static String installedJREs;
	public static String runtimeDefaultJRE;
	public static String configurationEditorPortsSection;
	public static String configurationEditorPortsDescription;
	public static String configurationEditorPortNameColumn;
	public static String configurationEditorPortValueColumn;

	static {
		NLS.initializeMessages(JettyUIPlugin.PLUGIN_ID + ".internal.Messages",
				Messages.class);
	}
}
