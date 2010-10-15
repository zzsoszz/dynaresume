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
package org.eclipse.gmt.modisco.jm2t.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;

/**
 * Interface for Java M2T Project.
 * 
 */
public interface IJM2TProject {

	public static final String NATURE_ID = JM2TCore.PLUGIN_ID + ".nature";

	/**
	 * Returns the owner {@link IProject}.
	 * 
	 * @return
	 */
	IProject getProject();

	/**
	 * Returns generator configurations defined for the Java M2T project.
	 * 
	 * @return
	 */
	IGeneratorConfiguration[] getGeneratorConfigurations();

	/**
	 * Fine the generator configuration by name.
	 * 
	 * @param name
	 * @return
	 */
	IGeneratorConfiguration findGeneratorConfiguration(String name);
}
