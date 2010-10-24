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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.util.StringUtils;
import org.eclipse.gmt.modisco.jm2t.internal.core.Messages;

/**
 * Default generator configuration validator.
 * 
 */
public class DefaultGeneratorConfigurationValidator implements
		IGeneratorConfigurationValidator {

	public static IGeneratorConfigurationValidator INSTANCE = new DefaultGeneratorConfigurationValidator();

	public IStatus validate(IGeneratorConfiguration generatorConfiguration,
			IJM2TProject project) {
		// Name is required?
		IStatus status = validateNameRequired(generatorConfiguration);
		if (!isOK(status)) {
			return status;
		}
		// Name is unique?
		status = validateNameUnique(generatorConfiguration, project);
		if (!isOK(status)) {
			return status;
		}
		// Template path required?
		status = validateTemplatePathRequired(generatorConfiguration, project);
		if (!isOK(status)) {
			return status;
		}
		// Template path exist?
		status = validateTemplatePathExists(generatorConfiguration, project);
		if (!isOK(status)) {
			return status;
		}
		// Target path required?
		status = validateTargetPathRequired(generatorConfiguration, project);
		if (!isOK(status)) {
			return status;
		}
		// Target path exist?
		status = validateTargetPathExists(generatorConfiguration, project);
		if (!isOK(status)) {
			return status;
		}

		return Status.OK_STATUS;
	}

	protected boolean isOK(IStatus status) {
		return status == null || status.isOK();
	}

	protected IStatus validateNameRequired(
			IGeneratorConfiguration generatorConfiguration) {
		String name = generatorConfiguration.getName();
		if (StringUtils.isEmpty(name)) {
			return JM2TCore
					.createErrorStatus(
							Messages.DefaultGeneratorConfigurationValidator_nameRequired,
							null);
		}
		return Status.OK_STATUS;
	}

	protected IStatus validateNameUnique(
			IGeneratorConfiguration generatorConfiguration, IJM2TProject project) {
		return Status.OK_STATUS;
	}

	protected IStatus validateTemplatePathRequired(
			IGeneratorConfiguration generatorConfiguration, IJM2TProject project) {
		IPath templatePath = generatorConfiguration.getTemplatePath();
		return validatePathRequired(
				templatePath,
				Messages.DefaultGeneratorConfigurationValidator_templatePathRequired);
	}

	protected IStatus validateTemplatePathExists(
			IGeneratorConfiguration generatorConfiguration, IJM2TProject project) {
		return validateFileExists(
				generatorConfiguration.getTemplatePath(),
				Messages.DefaultGeneratorConfigurationValidator_templatePathExists);
	}

	protected IStatus validateTargetPathRequired(
			IGeneratorConfiguration generatorConfiguration, IJM2TProject project) {
		IPath targetPath = generatorConfiguration.getTargetContainerPath();
		return validatePathRequired(
				targetPath,
				Messages.DefaultGeneratorConfigurationValidator_targetPathRequired);
	}

	protected IStatus validateTargetPathExists(
			IGeneratorConfiguration generatorConfiguration, IJM2TProject project) {
		return validateFolderExists(
				generatorConfiguration.getTargetContainerPath(),
				Messages.DefaultGeneratorConfigurationValidator_targetPathExists);
	}

	protected IStatus validatePathRequired(IPath path, String message) {
		if (path == null || path.isEmpty()) {
			return JM2TCore.createErrorStatus(message, null);
		}
		return Status.OK_STATUS;
	}

	protected IStatus validateFileExists(IPath path, String message) {
		if (path.isAbsolute()) {
			IContainer workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			IFile file = workspaceRoot.getFile(path);
			if (!file.exists()) {
				return JM2TCore.createErrorStatus(message, null);
			}
		}
		return Status.OK_STATUS;
	}

	protected IStatus validateFolderExists(IPath path, String message) {
		if (path.isAbsolute()) {
			IContainer workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			IFolder folder = workspaceRoot.getFolder(path);
			if (!folder.exists()) {
				return JM2TCore.createErrorStatus(message, null);
			}
		}
		return Status.OK_STATUS;
	}

}
