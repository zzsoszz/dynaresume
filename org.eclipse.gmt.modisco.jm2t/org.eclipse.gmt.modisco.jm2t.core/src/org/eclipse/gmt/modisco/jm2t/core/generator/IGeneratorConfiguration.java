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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * Generator configuration interface.
 * 
 */
public interface IGeneratorConfiguration {

	public static final IGeneratorConfiguration[] EMPTY = new IGeneratorConfiguration[0];

	/**
	 * Returns the name of the generator configuration.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Set the name of the generator configuration
	 * 
	 * @param name
	 */
	void setName(String name);
	
	/**
	 * Returns the description of the generator entry.
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * Returns the list of packages name of Java files which can generate code.
	 * 
	 * @return
	 */
	String[] getRawPackages();

	IStatus validate(IProgressMonitor monitor);

	IGeneratorType getGeneratorType();


}
