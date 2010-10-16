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

/**
 * Generator type interface.
 * 
 */
public interface IGeneratorType {

	/**
	 * Returns the id of this generator type. Each known generator type has a
	 * distinct id. Ids are intended to be used internally as keys; they are not
	 * intended to be shown to end users.
	 * 
	 * @return the generator type id
	 */
	String getId();

	/**
	 * Returns the displayable name for this generator type.
	 * <p>
	 * Note that this name is appropriate for the current locale.
	 * </p>
	 * 
	 * @return a displayable name for this generator type
	 */
	String getName();

	/**
	 * Returns the displayable description for this generator type.
	 * <p>
	 * Note that this description is appropriate for the current locale.
	 * </p>
	 * 
	 * @return a displayable description for this generator type
	 */
	String getDescription();

	/**
	 * Return the generator instance.
	 * 
	 * @return
	 */
	IGenerator<?> getGenerator();
}
