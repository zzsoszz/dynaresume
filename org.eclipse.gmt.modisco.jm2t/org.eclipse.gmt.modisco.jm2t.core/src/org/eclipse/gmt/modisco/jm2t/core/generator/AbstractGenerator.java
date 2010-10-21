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
package org.eclipse.gmt.modisco.jm2t.core.generator;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gmt.modisco.jm2t.core.util.ResourcesUtils;

/**
 * 
 * Abstract base class for generator.
 * 
 */
public abstract class AbstractGenerator implements IGenerator {

	public final void generate(Object model,
			IGeneratorConfiguration configuration) throws GeneratorException {
		IPath templatePath = configuration.getTemplatePath();
		IPath targetContainerPath = configuration.getTargetContainerPath();
		generate(model, templatePath, targetContainerPath, configuration);
	}

	protected File toFile(IPath path) throws GeneratorException {
		IContainer workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = workspaceRoot.getFile(path);
		try {
			return ResourcesUtils.toLocalFile(file.getLocationURI(), null);
		} catch (CoreException e) {
			throw new GeneratorException(e);
		}
	}

	protected IContainer toContainer(IPath path) {
		IContainer workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		return workspaceRoot.getFolder(path);
	}

	protected abstract void generate(Object model, IPath templatePath,
			IPath targetContainerPath, IGeneratorConfiguration configuration)
			throws GeneratorException;

}
