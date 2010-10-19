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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.core.xml.XMLWriter;

/**
 * Implementation for Generator launcher configurationS {@link IGeneratorConfiguration}.
 * 
 */
public class GeneratorConfiguration implements IGeneratorConfiguration {

	public static final String TAG_JM2T = "jm2t";
	public static final String TAG_GENCONFIGURATION = "generator-configuration";

	public static final String TAG_NAME = "name";
	public static final String TAG_GENERATOR_TYPE = "generatorType";
	
	private String name;
	private String description;
	
	private IGeneratorType generatorType;
	
	public GeneratorConfiguration(IGeneratorType generatorType) {
		this.generatorType = generatorType;
	}
	
	public String getName() {
		return name;
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
	
	public IGeneratorType getGeneratorType() {
		return generatorType;
	}

	public void elementEncode(XMLWriter writer, IPath fullPath,
			boolean indent, boolean newLine, Map unknownElements) {
		Map parameters = new HashMap();

		parameters.put(TAG_NAME,this.name);
		parameters.put(TAG_GENERATOR_TYPE,getGeneratorType().getId());
		writer.printTag(
				TAG_GENCONFIGURATION,
				parameters,
				indent,
				newLine,
				true);

		
	}
}
