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
 * Model provider type interface.
 * 
 */
public interface IModelProviderType {

	/**
	 * Returns the id of this modelprovider type. Each known modelprovider type
	 * has a distinct id. Ids are intended to be used internally as keys; they
	 * are not intended to be shown to end users.
	 * 
	 * @return the modelprovider type id
	 */
	String getId();

	/**
	 * Return the model provider instance.
	 * 
	 * @return
	 */
	IModelProvider getModelProvider();
}
