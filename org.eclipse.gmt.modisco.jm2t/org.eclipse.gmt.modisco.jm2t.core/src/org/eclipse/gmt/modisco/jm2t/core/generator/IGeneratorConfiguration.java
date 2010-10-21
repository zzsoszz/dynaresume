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
package org.eclipse.gmt.modisco.jm2t.core.generator;

import org.eclipse.core.runtime.IPath;

/**
 * Generator configuration interface.
 * 
 */
public interface IGeneratorConfiguration {

	public static final IGeneratorConfiguration[] EMPTY = new IGeneratorConfiguration[0];

	/**
	 * Returns the owner generator type.
	 */
	IGeneratorType getGeneratorType();

	/**
	 * Returns the model converter to use before launching generation.
	 * 
	 * @return
	 */
	IModelConverterType getModelConverterType();

	/**
	 * Returns the name of the generator configuration.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Set the name of the generator configuration.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Returns the description of the generator configuration.
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * Set the description of the generator configuration.
	 * 
	 * @param description
	 */
	void setDescription(String description);

	/**
	 * Returns the template Path to use to generate code.
	 * 
	 * @return
	 */
	IPath getTemplatePath();

	/**
	 * Set the template Path to use to generate code.
	 * 
	 * @param templatePath
	 */
	void setTemplatePath(IPath templatePath);

	/**
	 * Returns the target container Path where files must be generated.
	 * 
	 * @return
	 */
	IPath getTargetContainerPath();

	/**
	 * Set the target container Path where files must be generated.
	 * 
	 * @param targetContainer
	 */
	void setTargetContainerPath(IPath targetContainerPath);

	/**
	 * Returns the list of packages name of Java files which can generate code.
	 * 
	 * @return
	 */
	// String[] getRawPackages();

	// IStatus validate(IProgressMonitor monitor);

}
