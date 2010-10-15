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
package org.eclipse.gmt.modisco.jm2t.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JM2TUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gmt.modisco.jm2t.ui"; //$NON-NLS-1$

	// The shared instance
	private static JM2TUI plugin;
	
	/**
	 * The constructor
	 */
	public JM2TUI() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JM2TUI getDefault() {
		return plugin;
	}

	public static Object[] adaptLabelChangeObjects(Object[] obj) {
		if (obj == null)
			return obj;
		
		List<Object> list = new ArrayList<Object>();
		int size = obj.length;
		for (int i = 0; i < size; i++) {
//			if (obj[i] instanceof IModule) {
//				list.add(obj[i]);
//			} else if (obj[i] instanceof IServer) {
//				list.add(obj[i]);
//			} else if (obj[i] instanceof ModuleServer) {
//				list.add(obj[i]);
//			} else if (obj[i] instanceof IProject) {
//				IProject proj = (IProject) obj[i];
//				IModule[] m = ServerUtil.getModules(proj);
//				int size2 = m.length;
//				for (int j = 0; j < size2; j++)
//					list.add(m[j]);
//			}
		}
		
		Object[] o = new Object[list.size()];
		list.toArray(o);
		return o;
	}

}
