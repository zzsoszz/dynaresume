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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmt.modisco.jm2t.core.util.ProjectUtils;
import org.eclipse.gmt.modisco.jm2t.internal.core.JM2TCorePlugin;

public class JM2TProjectInstaller {

	/**
	 * Install JM2T Nature to the project
	 * 
	 * @param project
	 * @param progressMonitor
	 * @return
	 */
	public static IStatus installNature(IProject project,
			IProgressMonitor progressMonitor) {
		IStatus status = null;
		String natureId = JM2TNature.NATURE_ID;
		try {
			// Add Akrogen Nature to the project
			ProjectUtils.addNatureToProject(project, progressMonitor, natureId);
			status = new Status(IStatus.OK, JM2TCorePlugin.PLUGIN_ID,
					IStatus.OK, "", null);
		} catch (CoreException e) {
			status = new Status(IStatus.ERROR,
					JM2TCorePlugin.PLUGIN_ID, IStatus.OK,
					"Error adding nature " + natureId + " to project: "
							+ project.getName(), e);
		}

		return status;
	}

	/**
	 * Un-install JM2T Nature to the project
	 * 
	 * @param project
	 * @return
	 */
	public static IStatus uninstallNature(IProject project) {
		return uninstallNature(project, null);
	}

	/**
	 * Uninstall Akrogen Nature to the project
	 * 
	 * @param project
	 * @param progressMonitor
	 * @return
	 */
	public static IStatus uninstallNature(IProject project,
			IProgressMonitor progressMonitor) {
		IStatus status = null;
		String natureId = JM2TNature.NATURE_ID;
		try {
			// Remove JM2T Nature to the project
			ProjectUtils.removeNatureToProject(project, progressMonitor,
					natureId);
			status = new Status(IStatus.OK, JM2TCorePlugin.PLUGIN_ID,
					IStatus.OK, "", null);
		} catch (CoreException e) {
			status = new Status(IStatus.ERROR,
					JM2TCorePlugin.PLUGIN_ID, IStatus.OK,
					"Error remove nature " + natureId + " to project: "
							+ project.getName(), e);
		}

		return status;
	}

}
