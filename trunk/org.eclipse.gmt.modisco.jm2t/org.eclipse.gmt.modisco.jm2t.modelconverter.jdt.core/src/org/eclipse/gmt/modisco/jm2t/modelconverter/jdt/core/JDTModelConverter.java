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
package org.eclipse.gmt.modisco.jm2t.modelconverter.jdt.core;

import org.eclipse.gmt.modisco.jm2t.core.generator.AbstractModelConverter;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.ModelConverterException;

public class JDTModelConverter extends AbstractModelConverter {

	public Object selectModel(Object model) {
		// Get the JDT Java Element from the selected object model
		return JDTHelper.getJavaElement(model);
	}

	public Object convertModel(Object model,
			IGeneratorConfiguration configuration)
			throws ModelConverterException {
		return model;
	}
}
