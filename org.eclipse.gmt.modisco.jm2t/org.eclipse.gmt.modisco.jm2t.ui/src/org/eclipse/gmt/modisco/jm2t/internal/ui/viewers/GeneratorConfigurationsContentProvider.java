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
package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;

/**
 * Generator configurations content provider.
 */
public class GeneratorConfigurationsContentProvider extends BaseContentProvider {

	private IJM2TProject project;

	/**
	 * RuntimeContentProvider constructor comment.
	 */
	public GeneratorConfigurationsContentProvider(IJM2TProject project) {
		super();
		this.project = project;
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		return project.getGeneratorConfigurations();
	}
}
