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
package org.eclipse.gmt.modisco.jm2t.internal.core;

/**
 * Manager of Java M2T projects.
 * 
 */
public class JM2TModelManager {

	/**
	 * Unique handle onto the JM2TModel
	 */
	final JM2TModel jm2TModel = new JM2TModel();

	/**
	 * The singleton manager
	 */
	private static JM2TModelManager MANAGER = new JM2TModelManager();

	/**
	 * Returns the singleton JM2TModelManager
	 */
	public final static JM2TModelManager getJM2TModelManager() {
		return MANAGER;
	}

	/**
	 * Returns the handle to the active Java M2T Model.
	 */
	public JM2TModel getJM2TModel() {
		return this.jm2TModel;
	}
}
