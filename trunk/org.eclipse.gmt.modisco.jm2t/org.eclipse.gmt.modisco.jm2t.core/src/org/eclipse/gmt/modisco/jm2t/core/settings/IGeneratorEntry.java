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
package org.eclipse.gmt.modisco.jm2t.core.settings;

/**
 * Interface for Generator entry.
 * 
 */
public interface IGeneratorEntry {

	public static final IGeneratorEntry[] EMPTY_GENERATOR_ENTRY = new IGeneratorEntry[0];

	/**
	 * Returns the name of the generator entry.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Returns the description of the generator entry.
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * Returns the engine to use to generate code of the generator entry.
	 * 
	 * @return
	 */
	String getEngine();

	/**
	 * Returns the list of packages name of Java files which can generate code.
	 * 
	 * @return
	 */
	String[] getRawPackages();

}
