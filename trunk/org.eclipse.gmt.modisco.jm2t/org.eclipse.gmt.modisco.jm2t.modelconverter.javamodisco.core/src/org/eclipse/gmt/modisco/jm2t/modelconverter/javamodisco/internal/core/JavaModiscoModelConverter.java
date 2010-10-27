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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.infra.discoverymanager.DiscoveryParameter;
import org.eclipse.gmt.modisco.java.actions.DefaultDiscoverer;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.ModelConverterException;
import org.eclipse.gmt.modisco.jm2t.modelconverter.jdt.core.JDTModelConverter;
import org.eclipse.jdt.core.IJavaElement;

/**
 * JM2T Model converter to convert JDT JavaElement as EMF model with Java
 * Modisco.
 * 
 */
public class JavaModiscoModelConverter extends JDTModelConverter {

	@Override
	public Object convertModel(Object model,
			IGeneratorConfiguration configuration)
			throws ModelConverterException {
		// Get the JDT Java Element from the selected object model
		IJavaElement element = (IJavaElement) model;

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

}
