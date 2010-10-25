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
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverter;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterCategoryType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.core.util.StringUtils;

/**
 * Model converter type implementation which use Extension Point
 * modelConverterTypes.
 * 
 */
public class ModelConverterType implements IModelConverterType {

	private IConfigurationElement element;
	private IModelConverter modelConverter;

	/**
	 * ModelConverterType constructor comment.
	 * 
	 * @param element
	 *            a configuration element
	 */
	public ModelConverterType(IConfigurationElement element) {
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

	public String getName() {
		try {
			return element.getAttribute("name");
		} catch (Exception e) {
			return null;
		}
	}

	public String getDescription() {
		try {
			return element.getAttribute("description");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the category of this factory.
	 * 
	 * @return java.lang.String
	 */
	public String getCategoryId() {
		try {
			return element.getAttribute("categoryId");
		} catch (Exception e) {
			return null;
		}
	}

	public IModelConverter getModelConverter() {
		if (modelConverter == null) {
			modelConverter = createModelConverter();
		}
		return modelConverter;
	}

	private IModelConverter createModelConverter() {
		try {
			return (IModelConverter) element.createExecutableExtension("class");
		} catch (Exception e) {
			return null;
		}
	}

	public IModelConverterCategoryType getCategoryType() {
		String categoryId = getCategoryId();
		if (!StringUtils.isEmpty(categoryId)) {
			return JM2TCore.getGeneratorManager()
					.findModelConverterCategoryType(categoryId);
		}
		return null;
	}

	public void dispose() {
		element = null;
		modelConverter = null;
	}

}
