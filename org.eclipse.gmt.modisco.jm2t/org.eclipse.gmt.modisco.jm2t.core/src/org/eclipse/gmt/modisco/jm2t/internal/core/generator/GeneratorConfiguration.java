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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.core.xml.XMLWriter;

/**
 * Implementation for Generator launcher configurationS
 * {@link IGeneratorConfiguration}.
 * 
 */
public class GeneratorConfiguration implements IGeneratorConfiguration {

	public static final String TAG_JM2T = "jm2t";
	public static final String TAG_GENCONFIGURATION = "generatorConfiguration";

	public static final String TAG_NAME = "name";
	public static final String TAG_TEMPLATE_PATH = "templatePath";
	public static final String TAG_TARGET_PATH = "targetPath";
	public static final String TAG_GENERATOR_TYPE = "generatorType";
	public static final String TAG_MODEL_CONVERTER_TYPE = "modelConverterType";

	private String name;
	private String description;
	private IGeneratorType generatorType;
	private IModelConverterType modelConverterType;
	private IPath templatePath;
	private IPath targetContainerPath;
	private IJM2TProject project;

	public GeneratorConfiguration(IGeneratorType generatorType,
			IModelConverterType modelConverterType, IJM2TProject project) {
		this.generatorType = generatorType;
		this.modelConverterType = modelConverterType;
		this.project = project;
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

	public IModelConverterType getModelConverterType() {
		return modelConverterType;
	}

	public void elementEncode(XMLWriter writer, IPath fullPath, boolean indent,
			boolean newLine, Map unknownElements) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(TAG_NAME, this.name);
		IGeneratorType generatorType = getGeneratorType();
		if (generatorType != null) {
			parameters.put(TAG_GENERATOR_TYPE, generatorType.getId());
		}
		IModelConverterType modelConverterType = getModelConverterType();
		if (modelConverterType != null) {
			parameters.put(TAG_MODEL_CONVERTER_TYPE, modelConverterType.getId());
		}
		if (templatePath != null) {
			parameters.put(TAG_TEMPLATE_PATH, String.valueOf(templatePath));
		}
		if (targetContainerPath != null) {
			parameters
					.put(TAG_TARGET_PATH, String.valueOf(targetContainerPath));
		}
		writer.printTag(TAG_GENCONFIGURATION, parameters, indent, newLine, true);

	}

	public IContainer getTargetContainer() {
		return project.getProject();
	}

	public IPath getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(IPath templatePath) {
		this.templatePath = templatePath;
	}

	public IPath getTargetContainerPath() {
		return targetContainerPath;
	}

	public void setTargetContainerPath(IPath targetContainerPath) {
		this.targetContainerPath = targetContainerPath;
	}

}
