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
 * 
 * Model converter type interface.
 * 
 */
public interface IModelConverterType {

	public static final IModelConverterType[] EMPTY = new IModelConverterType[0];

	/**
	 * Returns the id of this modelconverter type. Each known modelconverter
	 * type has a distinct id. Ids are intended to be used internally as keys;
	 * they are not intended to be shown to end users.
	 * 
	 * @return the modelconverter type id
	 */
	String getId();

	/**
	 * Returns the category of this modelconverter type.
	 * 
	 * @return the modelconverter type category
	 */
	String getCategory();

	/**
	 * Return the model converter instance.
	 * 
	 * @return
	 */
	IModelConverter getModelConverter();
}
