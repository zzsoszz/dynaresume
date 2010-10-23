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
package org.eclipse.gmt.modisco.jm2t.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorManager;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.core.JM2TModel;
import org.eclipse.gmt.modisco.jm2t.internal.core.JM2TModelManager;
import org.eclipse.gmt.modisco.jm2t.internal.core.Trace;
import org.eclipse.gmt.modisco.jm2t.internal.core.generator.GeneratorManager;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JM2TCore extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gmt.modisco.jm2t.core"; //$NON-NLS-1$

	// The shared instance
	private static JM2TCore plugin;

	/**
	 * The constructor
	 */
	public JM2TCore() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		plugin = this;
		Trace.trace(Trace.CONFIG,
				"----->----- JM2T Core plugin startup ----->-----");
		GeneratorManager.getManager().initialize();
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		Trace.trace(Trace.CONFIG,
				"-----<----- JM2T Core plugin shutdown -----<-----");
		plugin = null;
		super.stop(context);
		GeneratorManager.getManager().destroy();
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static JM2TCore getDefault() {
		return plugin;
	}

	/**
	 * Returns the Java M2T project corresponding to the given project.
	 * 
	 * @param project
	 *            the given project
	 * @return the Java M2T projec project corresponding to the given project,
	 *         null if the given project is null
	 */
	public static IJM2TProject create(IProject project) {
		if (project == null) {
			return null;
		}
		JM2TModel jm2tModel = JM2TModelManager.getJM2TModelManager()
				.getJM2TModel();
		return jm2tModel.getJM2TProject(project);
	}

	/**
	 * Returns the generator manager.
	 * 
	 * @return the array of generator types {@link IGeneratorType}
	 */
	public static IGeneratorManager getGeneratorManager() {
		return GeneratorManager.getManager();
	}

	public static IStatus createErrorStatus(String message, Throwable exception) {
		return createStatus(IStatus.ERROR, 0, message, exception);
	}

	public static IStatus createStatus(int severity, int code, String message,
			Throwable exception) {
		return new Status(severity, JM2TCore.PLUGIN_ID, 0, message, exception);
	}

}
