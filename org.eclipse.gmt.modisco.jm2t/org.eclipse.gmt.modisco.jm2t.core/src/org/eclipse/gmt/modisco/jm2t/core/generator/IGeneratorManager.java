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
 * Generator manager interface.
 * 
 */
public interface IGeneratorManager {

	/**
	 * Returns an array of all known generator types.
	 * <p>
	 * A new array is returned on each call, so clients may store or modify the
	 * result.
	 * </p>
	 * 
	 * @return the array of generator types {@link IGeneratorType}
	 */
	IGeneratorType[] getGeneratorTypes();

	/**
	 * Returns the generator type with the given id, or <code>null</code> if
	 * none. This convenience method searches the list of known generator types
	 * ({@link #getGeneratorTypes()}) for the one with a matching generator type
	 * id ({@link IGeneratorType#getId()}). The id may not be null.
	 * 
	 * @param id
	 *            the generator type id
	 * @return the generator type, or <code>null</code> if there is no generator
	 *         type with the given id
	 */
	IGeneratorType findGeneratorType(String id);

	/**
	 * Returns an array of all known modelProvider types.
	 * <p>
	 * A new array is returned on each call, so clients may store or modify the
	 * result.
	 * </p>
	 * 
	 * @return the array of modelProvider types {@link IModelConverterType}
	 */
	IModelConverterType[] getModelConverterTypes();

	/**
	 * Returns the model converter type with the given id, or <code>null</code>
	 * if none. This convenience method searches the list of known model
	 * converter types ({@link #getModelConverterTypes()}) for the one with a
	 * matching model converter type id ({@link IModelConverterType#getId()}).
	 * The id may not be null.
	 * 
	 * @param id
	 *            the model converter type id
	 * @return the model converter type, or <code>null</code> if there is no
	 *         model converter type with the given id
	 */
	IModelConverterType findModelConverterType(String id);

	/**
	 * Generate .
	 * 
	 * @param model
	 * @param modelProvider
	 * @param generator
	 * @param generatorConfiguration
	 */
	void generate(final Object model,
			final IGeneratorConfiguration generatorConfiguration)
			throws GeneratorException;

}
