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
package org.eclipse.gmt.modisco.jm2t.modelprovider.internal.javamodisco;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.infra.discoverymanager.DiscoveryParameter;
import org.eclipse.gmt.modisco.java.actions.DefaultDiscoverer;
import org.eclipse.gmt.modisco.jm2t.core.generator.AbstractModelProvider;
import org.eclipse.jdt.core.IJavaElement;

public class JavaModiscoModelProvider extends AbstractModelProvider {

	public Object getModel(Object model) {
		IJavaElement element = (IJavaElement) model;
		IExtendedDiscover discoverer = DiscovererFactory.getFactory()
				.createDiscoverer(element);

		// Parameters of the discoverer
		Map<DiscoveryParameter, Object> parameters = new HashMap<DiscoveryParameter, Object>();
		parameters.put(DefaultDiscoverer.PARAMETER_SILENT_MODE, true);
		parameters.put(DefaultDiscoverer.PARAMETER_BROWSE_RESULT, false);

		// Process 
		discoverer.discoverElement(discoverer.getSource(), parameters);

		// Get the result model
		Resource javaModel = (Resource) parameters
				.get(DefaultDiscoverer.PARAMETER_TARGET_RESOURCE);
		EObject emfObject = javaModel.getContents().get(0);
		return emfObject;
	}

}
