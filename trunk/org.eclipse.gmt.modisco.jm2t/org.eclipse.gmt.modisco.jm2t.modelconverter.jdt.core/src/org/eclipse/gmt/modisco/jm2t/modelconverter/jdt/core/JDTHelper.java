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
package org.eclipse.gmt.modisco.jm2t.modelconverter.jdt.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.gmt.modisco.jm2t.core.generator.ModelConverterException;
import org.eclipse.gmt.modisco.jm2t.modelconverter.jdt.internal.core.Messages;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;

public class JDTHelper {

	/**
	 * Retrieve {@link IJavaElement} from the selected object model.
	 * 
	 * @param model
	 * @return
	 * @throws ModelConverterException
	 */
	public static IJavaElement getJavaElement(Object model)
			throws ModelConverterException {
		if (model instanceof IJavaElement) {
			return (IJavaElement) model;
		}
		try {
			if (model instanceof IProject) {
				return JavaCore.create((IProject) model);
			}
			if (model instanceof IFile) {
				return JavaCore.create((IFile) model);
			}
			if (model instanceof IFolder) {
				return JavaCore.create((IFolder) model);
			}
			if (model instanceof IResource) {
				return JavaCore.create((IResource) model);
			}
			if (model instanceof IWorkspaceRoot) {
				return JavaCore.create((IWorkspaceRoot) model);
			}
		} catch (Throwable e) {
			throw new ModelConverterException(e);
		}
		throw new ModelConverterException(
				Messages.CannotConvertToJavaELement);
	}
}
