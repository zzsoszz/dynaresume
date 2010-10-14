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
package org.eclipse.gmt.modisco.jm2t.core.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * 
 * Static utility methods for manipulating Eclipse resources {@link IResource},
 * {@link IFile}.
 * 
 */
public class ResourcesUtils {

	/**
	 * Return the {@link IResource} from the <code>selection</code>.
	 * 
	 * @param selection
	 * @return
	 */
	public static IResource getResource(ISelection selection) {
		if (selection == null || !(selection instanceof IStructuredSelection))
			return null;
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		Object element = structuredSelection.getFirstElement();
		if (element instanceof IResource)
			return (IResource) element;
		if (!(element instanceof IAdaptable)) {
			return null;
		} else {
			IAdaptable adaptable = (IAdaptable) element;
			Object adapter = adaptable
					.getAdapter(org.eclipse.core.resources.IResource.class);
			return (IResource) adapter;
		}
	}

	/**
	 * Return the {@link IFile} from the <code>selection</code>.
	 * 
	 * @param selection
	 * @return
	 */
	public static IFile getFile(ISelection selection) {
		IResource resource = getResource(selection);
		if (resource instanceof IFile)
			return (IFile) resource;
		return null;
	}

}
