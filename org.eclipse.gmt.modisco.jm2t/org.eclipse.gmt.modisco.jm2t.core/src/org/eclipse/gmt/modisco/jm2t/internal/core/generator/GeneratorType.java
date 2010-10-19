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
import org.eclipse.gmt.modisco.jm2t.core.generator.IGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelProviderType;

/**
 * Generator type implementation which use Extension Point generatorTypes.
 * 
 */
public class GeneratorType implements IGeneratorType {

	private IConfigurationElement element;
	private IGenerator<?> generator;

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

	public IGenerator<?> getGenerator() {
		if (generator == null) {
			generator = createGenerator();
		}
		return generator;
	}

	private IGenerator<?> createGenerator() {
		try {
			return (IGenerator<?>) element.createExecutableExtension("class");
		} catch (Exception e) {
			return null;
		}
	}
	
	public IGeneratorConfiguration createGeneratorConfiguration() {
		return new GeneratorConfiguration(this);
	}

	public IModelProviderType getModelProviderType() {
		String modelProviderTypeId = element
				.getAttribute("modelProviderTypeId");
		return GeneratorManager.getManager().findModelProviderType(
				modelProviderTypeId);
	}
	
	public void dispose() {
		element = null;
		generator = null;
	}
}
