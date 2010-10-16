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
package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;

/**
 * Generator type content provider.
 */
public class GeneratorTypeContentProvider extends BaseContentProvider {

	private IGeneratorType[] generatorTypes;

	public GeneratorTypeContentProvider(IGeneratorType[] generatorTypes) {
		this.generatorTypes = generatorTypes;
	}

	public Object[] getElements(Object arg0) {
		return generatorTypes;
	}

}
