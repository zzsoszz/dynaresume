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
package org.eclipse.gmt.modisco.jm2t.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.JM2TProjectInstaller;

/**
 * Add Freemarker nature to the selected Eclipse Project.
 * 
 */
public class AddJM2TNatureActionDelegate extends
		AbstractJM2TNatureActionDelegate {

	/**
	 * Add Freemarker nature to the selected project.
	 * 
	 * @return
	 */
	protected IStatus doRun() {
		IProject project = super.getSelectedProject();
		if (project != null)
			return JM2TProjectInstaller.installNature(project, null);
		return null;
	}
}
