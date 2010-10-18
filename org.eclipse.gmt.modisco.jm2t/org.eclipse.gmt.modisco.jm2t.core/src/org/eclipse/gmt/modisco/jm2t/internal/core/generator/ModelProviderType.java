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
package org.eclipse.gmt.modisco.jm2t.internal.core.generator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelProvider;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelProviderType;

/**
 * Model provider type implementation which use Extension Point modelProviderTypes.
 * 
 */
public class ModelProviderType implements IModelProviderType {

	private IConfigurationElement element;
	private IModelProvider modelProvider;

	/**
	 * ModelProviderType constructor comment.
	 * 
	 * @param element
	 *            a configuration element
	 */
	public ModelProviderType(IConfigurationElement element) {
		super();
		this.element = element;
	}

	/**
	 * Returns the id of this factory.
	 * 
	 * @return java.lang.String
	 */
	public String getId() {
		try {
			return element.getAttribute("id");
		} catch (Exception e) {
			return null;
		}
	}

	public IModelProvider getModelProvider() {
		if (modelProvider == null) {
			modelProvider = createModelProvider();
		}
		return modelProvider;
	}

	private IModelProvider createModelProvider() {
		try {
			return (IModelProvider) element.createExecutableExtension("class");
		} catch (Exception e) {
			return null;
		}
	}

	public void dispose() {
		element = null;
	}

}
