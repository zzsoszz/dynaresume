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
package org.eclipse.gmt.modisco.jm2t.internal.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;

/**
 * Java M2T Model which manage Java M2T project.
 * 
 */
public class JM2TModel {

	/**
	 * Returns the active JM2T project associated with the specified resource,
	 * or <code>null</code> if no JM2T project yet exists for the resource.
	 * 
	 * @exception IllegalArgumentException
	 *                if the given resource is not one of an IProject, IFolder,
	 *                or IFile.
	 */
	public IJM2TProject getJM2TProject(IResource resource) {
		switch (resource.getType()) {
		case IResource.FOLDER:
			return getJM2TProject(((IFolder) resource).getProject());
		case IResource.FILE:
			return getJM2TProject(((IFile) resource).getProject());
		case IResource.PROJECT:
			return getJM2TProject(((IProject) resource));
		default:
			throw new IllegalArgumentException(
					Messages.element_invalidResourceForProject);
		}
	}

	/**
	 * Returns the active JM2T project associated with the specified project
	 * which have JM2T Nature.
	 * 
	 * @param project
	 * @return
	 */
	public IJM2TProject getJM2TProject(IProject project) {
		try {
			if (!project.hasNature(IJM2TProject.NATURE_ID)) {
				return null;
			}
			JM2TProject jm2tProject = (JM2TProject) project
					.getSessionProperty(JM2TProject.JM2T_PROJECT);
			if (jm2tProject == null) {
				jm2tProject = new JM2TProject();
				jm2tProject.setProject(project);
				jm2tProject.configure();
			}
			return jm2tProject;
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}
}
