/*******************************************************************************
 * Copyright (c) 2009 Martin Lippert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Martin Lippert                   initial implementation      
 *   Angelo ZERR                      manage springweaver into Jpa context
 *******************************************************************************/
package org.eclipse.equinox.weaving.springweaver;

import static org.eclipse.equinox.weaving.springweaver.util.StringUtils.isEmpty;

import java.util.List;
import java.util.Properties;

import org.eclipse.equinox.weaving.springweaver.util.PropertiesUtils;

/**
 * Registry wich store the properties of the files 'springweaver.properties'
 * coming from OSGi fragment and 'springweaver-default.properties' of this
 * bundle.
 * 
 * Thoses files store as property name a key (ex : ECLIPSELINK) and as property
 * value, import packages (ex :
 * org.eclipse.persistence.descriptors.changetracking,...)
 * 
 */
public class DynamicImportPackagesRegistry {

	// springweaver-default.properties
	private static final String DEFAULT_SPRINGWEAVER_PROPERTIES_FILE = "springweaver-default.properties";
	private final Properties DEFAULT_DYNAMIC_IMPORTPACKAGES_PROPERTIES = new Properties();

	// springweaver.properties
	private static final String SPRINGWEAVER_PROPERTIES_FILE = "springweaver.properties";
	private final Properties DYNAMIC_IMPORTPACKAGES_PROPERTIES = new Properties();

	/**
	 * Initialize properties files springweaver-default.properties and
	 * springweaver.properties (coming from OSGi fragment).
	 */
	public void initialize() {
		ClassLoader cl = DynamicImportPackagesRegistry.class.getClassLoader();
		// Load springweaver-default.properties
		PropertiesUtils.loadProperties(
				DEFAULT_DYNAMIC_IMPORTPACKAGES_PROPERTIES,
				DEFAULT_SPRINGWEAVER_PROPERTIES_FILE, cl);
		// Load springweaver.properties (from OSGi fragment)
		PropertiesUtils.loadProperties(DYNAMIC_IMPORTPACKAGES_PROPERTIES,
				SPRINGWEAVER_PROPERTIES_FILE, cl);
	}

	/**
	 * Return packages from the springweaver.properties and
	 * springweaver-default.properties with list of key propertyName.
	 * 
	 * @param dynamicImportPackagesList
	 * @return
	 */
	public String getDynamicImportPackages(
			List<String> dynamicImportPackagesList) {
		if (dynamicImportPackagesList == null)
			return null;

		StringBuffer dynamicImportPackages = null;
		boolean endPackages = false;
		// Loop for dynamic-import packages list
		for (String packageKey : dynamicImportPackagesList) {

			String packages = getDynamicImportPackages(packageKey);
			if (!isEmpty(packages)) {
				if (dynamicImportPackages == null) {
					dynamicImportPackages = new StringBuffer();
				} else {
					if (!endPackages) {
						dynamicImportPackages.append(",");
					}
				}
				dynamicImportPackages.append(packages);
				endPackages = packages.endsWith(",");

			}
		}
		return dynamicImportPackages.toString();
	}

	/**
	 * Return packages from the springweaver.properties and
	 * springweaver-default.properties with key propertyName.
	 * 
	 * @param propertyName
	 * @return
	 */
	public String getDynamicImportPackages(String propertyName) {
		String packages = DYNAMIC_IMPORTPACKAGES_PROPERTIES
				.getProperty(propertyName);
		if (!isEmpty(packages))
			return packages;
		return DEFAULT_DYNAMIC_IMPORTPACKAGES_PROPERTIES
				.getProperty(propertyName);
	}

}
