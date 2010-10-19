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
import org.eclipse.core.runtime.Status;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.core.Trace;
import org.eclipse.gmt.modisco.jm2t.internal.core.generator.GeneratorConfiguration;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLSettingsLoader extends DefaultHandler {

	private List<IGeneratorConfiguration> generatorConfigurations = new ArrayList<IGeneratorConfiguration>();

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
		GeneratorConfiguration configuration = null;
		String type = atts.getValue(GeneratorConfiguration.TAG_GENERATOR_TYPE);
		IGeneratorType generatorType = JM2TCore.getGeneratorManager()
				.findGeneratorType(type);
		if (generatorType != null) {
			configuration = new GeneratorConfiguration(generatorType);
			configuration.setName(atts
					.getValue(GeneratorConfiguration.TAG_NAME));
		}
		return configuration;
	}

	public Collection<IGeneratorConfiguration> getGeneratorConfigurations() {
		return generatorConfigurations;
	}
}
