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
package org.eclipse.gmt.modisco.jm2t.generator.freemarker.internal.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.gmt.modisco.jm2t.core.generator.AbstractGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;

public class FreemarkerGenerator extends AbstractGenerator {

	@Override
	protected void generate(Object model, IPath templatePath,
			IPath targetContainerPath, IGeneratorConfiguration configuration) {

		System.out.println("Freemarker");
		System.out.println(model);

	}

}
