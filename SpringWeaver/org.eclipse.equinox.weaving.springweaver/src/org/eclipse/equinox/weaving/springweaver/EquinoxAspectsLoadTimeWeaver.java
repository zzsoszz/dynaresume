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

import java.lang.instrument.ClassFileTransformer;
import java.util.List;

import org.eclipse.equinox.service.weaving.IWeavingService;
import org.eclipse.equinox.service.weaving.IWeavingServiceFactory;
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

	private WeaverScope weaverScope = WeaverScope.BUNDLE;
	private List<String> dynamicImportPackagesList = null;

	private final ClassLoader classLoader;
	private BundleContext bundleContext = null;

	public EquinoxAspectsLoadTimeWeaver() {
		this(ClassUtils.getDefaultClassLoader());
	}

	public EquinoxAspectsLoadTimeWeaver(ClassLoader classLoader) {
		this.classLoader = classLoader;
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
		this.dynamicImportPackagesList = dynamicImportPackagesList;
	}

	// ----------- Spring LoadTimeWeaver implementation

	public void addTransformer(ClassFileTransformer transformer) {
		Activator.getInstance().getTransformerRegistry().addTransformer(
				this.weaverScope, this.bundleContext.getBundle(), transformer,
				this.dynamicImportPackagesList);
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

}
