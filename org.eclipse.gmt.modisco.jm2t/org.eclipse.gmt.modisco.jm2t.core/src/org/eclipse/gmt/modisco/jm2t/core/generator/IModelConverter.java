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
 * Model converter interface used to convert before launching generation the
 * selected Model from the Eclispe Package Explorer (ex : JDT ICompilationUnit)
 * to an another model (ex : Java Modisco EMF).
 * 
 */
public interface IModelConverter {

	/**
	 * Convert model to another model.
	 * 
	 * @param model
	 * @param configuration
	 * @return
	 */
	Object convert(Object model, IGeneratorConfiguration configuration);

}
