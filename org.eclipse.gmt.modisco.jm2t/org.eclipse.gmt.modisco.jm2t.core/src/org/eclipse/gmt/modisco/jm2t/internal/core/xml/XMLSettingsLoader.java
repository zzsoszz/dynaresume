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
package org.eclipse.gmt.modisco.jm2t.internal.core.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.core.util.StringUtils;
import org.eclipse.gmt.modisco.jm2t.internal.core.Trace;
import org.eclipse.gmt.modisco.jm2t.internal.core.generator.GeneratorConfiguration;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLSettingsLoader extends DefaultHandler {

	private List<IGeneratorConfiguration> generatorConfigurations = new ArrayList<IGeneratorConfiguration>();

	private IJM2TProject project;

	public XMLSettingsLoader(IJM2TProject project) {
		this.project = project;
	}

	/**
	 * Load XML config from file.
	 * 
	 * @param metadataFile
	 * @throws CoreException
	 */
	public void load(File metadataFile) throws CoreException {
		InputStream stream = null;
		try {
			stream = new FileInputStream(metadataFile);
			load(stream);
		} catch (FileNotFoundException e) {
			IStatus status = new Status(IStatus.ERROR, JM2TCore.PLUGIN_ID,
					e.getMessage(), e);
			throw new CoreException(status);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {

				}
			}
		}
	}

	/**
	 * Load XML config from stream.
	 * 
	 * @param stream
	 */
	public void load(InputStream stream) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(stream, this);
		} catch (Throwable t) {
			Trace.trace(Trace.SEVERE, t.getMessage(), t);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (GeneratorConfiguration.TAG_GENCONFIGURATION.equals(qName)) {
			IGeneratorConfiguration configuration = createGeneratorConfiguration(atts);
			if (configuration != null) {
				generatorConfigurations.add(configuration);
			}
		}
	}

	private IGeneratorConfiguration createGeneratorConfiguration(Attributes atts) {
		IGeneratorConfiguration configuration = null;
		String type = atts.getValue(GeneratorConfiguration.TAG_GENERATOR_TYPE);
		IGeneratorType generatorType = JM2TCore.getGeneratorManager()
				.findGeneratorType(type);
		if (generatorType != null) {

			IModelConverterType modelConverterType = getModelConverterType(
					generatorType, atts);
			configuration = generatorType.createGeneratorConfiguration(
					modelConverterType, project);
			configuration.setName(atts
					.getValue(GeneratorConfiguration.TAG_NAME));
			String templatePath = atts
					.getValue(GeneratorConfiguration.TAG_TEMPLATE_PATH);
			if (templatePath != null) {
				configuration.setTemplatePath(new Path(templatePath));
			}
			String targetPath = atts
					.getValue(GeneratorConfiguration.TAG_TARGET_PATH);
			if (targetPath != null) {
				configuration.setTargetContainerPath(new Path(targetPath));
			}
		}
		return configuration;
	}

	private IModelConverterType getModelConverterType(
			IGeneratorType generatorType, Attributes atts) {
		IModelConverterType modelConverterType = null;
		String converterType = atts
				.getValue(GeneratorConfiguration.TAG_MODEL_CONVERTER_TYPE);
		if (!StringUtils.isEmpty(converterType)) {
			modelConverterType = JM2TCore.getGeneratorManager()
					.findModelConverterType(converterType);
		}
		if (modelConverterType == null) {
			IModelConverterType[] supportedModelConverterTypes = generatorType
					.getSupportedModelConverterTypes();
			if (supportedModelConverterTypes.length > 0) {
				modelConverterType = supportedModelConverterTypes[0];
			}
		}
		return modelConverterType;
	}

	public Collection<IGeneratorConfiguration> getGeneratorConfigurations() {
		return generatorConfigurations;
	}
}
