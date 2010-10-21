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
package org.eclipse.gmt.modisco.jm2t.generator.acceleo3.internal.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.jm2t.core.generator.AbstractGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;

public class Acceleo3Generator extends AbstractGenerator {

	@Override
	protected void generate(Object model, IPath templatePath,
			IPath targetContainerPath, IGeneratorConfiguration configuration) {
		// Get the EMF Object
		EObject emfModel = getEObject(model);

		System.out.println("Acceleo3");
		System.out.println(model);

	}

	private EObject getEObject(Object model) {
		if (model instanceof EObject) {
			return (EObject) model;
		}
		if (model instanceof Resource) {
			return ((Resource) model).getContents().get(0);
		}
		return null;
	}

}
