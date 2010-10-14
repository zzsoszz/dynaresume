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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.settings.IGeneratorEntry;

/**
 * Implementation for Java M2T project {@link IJM2TProject}.
 * 
 */
public class JM2TProject implements IJM2TProject, IProjectNature {

	static final QualifiedName JM2T_PROJECT = new QualifiedName(
			JM2TCore.PLUGIN_ID + ".sessionprops", "jm2tProject");

	private IProject project;

	public void configure() throws CoreException {
		project.setSessionProperty(JM2T_PROJECT, this);
	}

	public void deconfigure() throws CoreException {
		project.setSessionProperty(JM2T_PROJECT, null);
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public IGeneratorEntry[] getRawGenerator() {
		return IGeneratorEntry.EMPTY_GENERATOR_ENTRY;
	}

	public IGeneratorEntry findGenerator(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
