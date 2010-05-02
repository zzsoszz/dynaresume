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

import java.lang.instrument.ClassFileTransformer;
import java.util.List;
import java.util.Properties;

import org.eclipse.equinox.service.weaving.IWeavingService;
import org.eclipse.equinox.service.weaving.IWeavingServiceFactory;
import org.eclipse.equinox.weaving.springweaver.util.PropertiesUtils;
import org.osgi.framework.BundleContext;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.SimpleThrowawayClassLoader;
import org.springframework.osgi.context.BundleContextAware;
import org.springframework.util.ClassUtils;

/**
 * Spring {@link LoadTimeWeaver} implementation for Equinox by using
 * {@link IWeavingServiceFactory} and {@link IWeavingService} from
 * Equinox-Aspects.
 * 
 * Ex :
 * 
 * <code><bean	class="org.eclipse.equinox.weaving.springweaver.EquinoxAspectsLoadTimeWeaver">
	property name="weaverScope" value="APPLICATION" />
	</bean>
	</code>
 */
public class EquinoxAspectsLoadTimeWeaver implements LoadTimeWeaver,
		BundleContextAware {

	// springweaver-default.properties
	private static final String DEFAULT_SPRINGWEAVER_PROPERTIES_FILE = "springweaver-default.properties";
	private final static Properties DYNAMIC_IMPORTPACKAGES_PROPERTIES = new Properties();

	// springweaver.properties
	private static final String SPRINGWEAVER_PROPERTIES_FILE = "springweaver.properties";
	private final static Properties DEFAULT_DYNAMIC_IMPORTPACKAGES_PROPERTIES = new Properties();

	private WeaverScope weaverScope = WeaverScope.BUNDLE;
	private StringBuffer dynamicImportPackages = null;

	private final ClassLoader classLoader;
	private BundleContext bundleContext = null;

	static {
		// Initialize properties files springweaver-default.properties and
		// springweaver.properties (coming from OSGi fragm
		initializeSpringweaverProperties();
	}

	public EquinoxAspectsLoadTimeWeaver() {
		this(ClassUtils.getDefaultClassLoader());
	}

	public EquinoxAspectsLoadTimeWeaver(ClassLoader classLoader) {
		this.classLoader = classLoader;
		this.dynamicImportPackages = new StringBuffer();
	}

	/**
	 * Set {@link WeaverScope}.
	 * 
	 * @param weaverScope
	 */
	public void setWeaverScope(WeaverScope weaverScope) {
		this.weaverScope = weaverScope;
	}

	/**
	 * Set dynamic-import packages list. This list contains key (ex : ECLIPLINK)
	 * from springweaver.properties files and springweaver-default.properties.
	 * 
	 * @param dynamicImportPackagesList
	 */
	public void setDynamicImportPackages(List<String> dynamicImportPackagesList) {

		// Loop for dynamic-import packages list
		for (String packageKey : dynamicImportPackagesList) {

			String packages = getDynamicImportPackages(packageKey);
			if (!isEmpty(packages)) {
				String currentPackages = dynamicImportPackages.toString();
				if (!isEmpty(currentPackages) && !currentPackages.endsWith(",")) {
					dynamicImportPackages.append(",");
				}
				dynamicImportPackages.append(packages);
			}
		}
	}

	/**
	 * Return packages from the springweaver.properties and
	 * springweaver-default.properties with key propertyName.
	 * 
	 * @param propertyName
	 * @return
	 */
	private String getDynamicImportPackages(String propertyName) {
		String packages = DYNAMIC_IMPORTPACKAGES_PROPERTIES
				.getProperty(propertyName);
		if (!isEmpty(packages))
			return packages;
		return DEFAULT_DYNAMIC_IMPORTPACKAGES_PROPERTIES
				.getProperty(propertyName);
	}

	// ----------- Spring LoadTimeWeaver implementation

	public void addTransformer(ClassFileTransformer transformer) {
		Activator.getInstance().getTransformerRegistry().addTransformer(
				this.weaverScope, this.bundleContext.getBundle(), transformer,
				this.dynamicImportPackages.toString());
		System.out.println("transformer added; " + transformer);
	}

	public ClassLoader getInstrumentableClassLoader() {
		return this.classLoader;
	}

	public ClassLoader getThrowawayClassLoader() {
		return new SimpleThrowawayClassLoader(getInstrumentableClassLoader());
	}

	// ----------- Spring BundleContextAware implementation

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	/**
	 * Initialize properties files springweaver-default.properties and
	 * springweaver.properties (coming from OSGi fragment).
	 */
	private static void initializeSpringweaverProperties() {
		ClassLoader cl = EquinoxAspectsLoadTimeWeaver.class.getClassLoader();
		// Load springweaver-default.properties
		PropertiesUtils.loadProperties(
				DEFAULT_DYNAMIC_IMPORTPACKAGES_PROPERTIES,
				DEFAULT_SPRINGWEAVER_PROPERTIES_FILE, cl);
		// Load springweaver.properties (from OSGi fragment)
		PropertiesUtils.loadProperties(DYNAMIC_IMPORTPACKAGES_PROPERTIES,
				SPRINGWEAVER_PROPERTIES_FILE, cl);
	}

}
