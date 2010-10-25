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
package org.eclipse.gmt.modisco.jm2t.modelconverter.javamodisco.internal.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.infra.discoverymanager.DiscoveryParameter;
import org.eclipse.gmt.modisco.java.actions.DefaultDiscoverer;
import org.eclipse.gmt.modisco.jm2t.core.generator.AbstractModelConverter;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.ModelConverterException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;

/**
 * JM2T Model converter to convert JDT JavaElement as EMF model with Java
 * Modisco.
 * 
 */
public class JavaModiscoModelConverter extends AbstractModelConverter {

	public Object selectModel(Object model) {
		// Get the JDT Java Element from the selected object model
		return getJavaElement(model);
	}
	
	public Object convertModel(Object model, IGeneratorConfiguration configuration)
			throws ModelConverterException {
		// Get the JDT Java Element from the selected object model
		IJavaElement element = (IJavaElement)model;

		// Get Modisco Discover switch the JDT JavaElement (IJavaProject,
		// ICompilationUnit)
		IExtendedDiscover discoverer = DiscovererFactory.getFactory()
				.createDiscoverer(element);

		// Initialize parameters of the discoverer
		Map<DiscoveryParameter, Object> parameters = new HashMap<DiscoveryParameter, Object>();
		parameters.put(DefaultDiscoverer.PARAMETER_SILENT_MODE, true);
		parameters.put(DefaultDiscoverer.PARAMETER_BROWSE_RESULT, false);

		// Transform JDT JavaElement to EMF Java Model Modisco instance
		discoverer.discoverElement(discoverer.getSource(), parameters);

		// Get and returns the result EMF model
		Resource javaModel = (Resource) parameters
				.get(DefaultDiscoverer.PARAMETER_TARGET_RESOURCE);
		EObject emfObject = javaModel.getContents().get(0);
		return emfObject;
	}

	/**
	 * Retrieve {@link IJavaElement} from the selected object model.
	 * 
	 * @param model
	 * @return
	 * @throws ModelConverterException
	 */
	private IJavaElement getJavaElement(Object model)
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
