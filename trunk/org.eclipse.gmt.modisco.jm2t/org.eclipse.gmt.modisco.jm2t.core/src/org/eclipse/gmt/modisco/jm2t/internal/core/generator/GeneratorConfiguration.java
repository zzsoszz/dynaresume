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
package org.eclipse.gmt.modisco.jm2t.internal.core.generator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;

/**
 * Implementation for Generator launcher configurationS {@link IGeneratorConfiguration}.
 * 
 */
public class GeneratorConfiguration implements IGeneratorConfiguration {

	private String name;
	private String description;
	private String engine;

	public String getName() {
		return name;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getRawPackages() {
		return null;
	}
	
	public IStatus validate(IProgressMonitor monitor) {
		return null;
	}
	
	public IGeneratorConfiguration save(boolean force, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		return this;
	}

}
