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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.generator.DefaultGeneratorConfigurationValidator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfigurationValidator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.core.util.StringUtils;

/**
 * Generator type implementation which use Extension Point generatorTypes.
 * 
 */
public class GeneratorType implements IGeneratorType {

	private IConfigurationElement element;
	private IGenerator generator;
	private IGeneratorConfigurationValidator validator;
	private IModelConverterType[] supportedModelConverterTypes;

	/**
	 * GeneratorType constructor comment.
	 * 
	 * @param element
	 *            a configuration element
	 */
	public GeneratorType(IConfigurationElement element) {
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

	public String getModelConverterCategories() {
		try {
			return element.getAttribute("modelConverterCategories");
		} catch (Exception e) {
			return null;
		}
	}

	public String getVendor() {
		try {
			return element.getAttribute("vendor");
		} catch (Exception e) {
			return null;
		}
	}

	public IGenerator getGenerator() {
		if (generator == null) {
			generator = createGenerator();
		}
		return generator;
	}

	private IGenerator createGenerator() {
		try {
			return (IGenerator) element.createExecutableExtension("class");
		} catch (Exception e) {
			return null;
		}
	}

	public IGeneratorConfigurationValidator getValidator() {
		if (validator == null) {
			validator = createValidator();
			if (validator == null) {
				validator = DefaultGeneratorConfigurationValidator.INSTANCE;
			}
		}
		return validator;
	}

	private IGeneratorConfigurationValidator createValidator() {
		try {
			return (IGeneratorConfigurationValidator) element
					.createExecutableExtension("validatorClass");
		} catch (Exception e) {
			return null;
		}
	}

	public IGeneratorConfiguration createGeneratorConfiguration(
			IModelConverterType converterType, IJM2TProject project) {
		return new GeneratorConfiguration(this, converterType, project);
	}

	public IModelConverterType[] getSupportedModelConverterTypes() {
		if (supportedModelConverterTypes == null) {
			String categories = getModelConverterCategories();
			if (StringUtils.isEmpty(categories)) {
				supportedModelConverterTypes = GeneratorManager.getManager()
						.getModelConverterTypes();
			} else {
				List<IModelConverterType> filteredModelConverterTypes = new ArrayList<IModelConverterType>();
				String category = null;
				String[] tokenizedCategories = StringUtils.tokenize(categories,
						",");
				for (int i = 0; i < tokenizedCategories.length; i++) {
					category = tokenizedCategories[i];
					GeneratorManager.getManager()
							.findModelConverterTypesByCategory(category,
									filteredModelConverterTypes);
				}
				supportedModelConverterTypes = filteredModelConverterTypes
						.toArray(IModelConverterType.EMPTY);
			}
		}
		return supportedModelConverterTypes;
	}

	public IStatus validate(IGeneratorConfiguration generatorConfiguration,
			IJM2TProject project) {
		return getValidator().validate(generatorConfiguration, project);
	}

	public void dispose() {
		element = null;
		generator = null;
		validator = null;
		supportedModelConverterTypes = null;
	}
}
