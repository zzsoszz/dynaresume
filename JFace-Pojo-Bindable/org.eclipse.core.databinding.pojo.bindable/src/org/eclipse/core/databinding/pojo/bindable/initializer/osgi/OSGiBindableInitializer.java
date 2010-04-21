/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.initializer.osgi;

import java.util.Collection;
import java.util.Properties;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.initializer.AbstractBindableInitializer;
import org.eclipse.core.databinding.pojo.bindable.internal.util.StringUtils;
import org.eclipse.core.databinding.pojo.bindable.logs.Policy;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * OSGi Bindable Initializer which register a service
 * {@link OSGiBindableInitializer} into OSGi registry services which is enable
 * to transform Class into Pojo Bindable. This initializer must be created into
 * another bundle :
 * 
 * <ul>
 * <li>{@link OSGiBindableInitializer#start()} must be called into
 * {@link BundleActivator#start(BundleContext)}.</li>
 * <li>{@link OSGiBindableInitializer#stop()} must be called into
 * {@link BundleActivator#stop(BundleContext)}.</li>
 * </ul>
 * 
 * {@link BindableStrategy} is built by using "bindable.properties" file comming
 * from a OSGi fragment attached to the bundle which create an instance of
 * {@link OSGiBindableInitializer}.
 */
public abstract class OSGiBindableInitializer extends
		AbstractBindableInitializer {

	protected BundleContext bundleContext = null;
	protected OSGiBindableStrategy bindableStrategy = null;
	protected ServiceRegistration serviceRegistration = null;

	/**
	 * Constructor wait the {@link BundleContext} comming from the Bundle wich
	 * create {@link OSGiBindableInitializer}.
	 * 
	 * @param bundleContext
	 */
	public OSGiBindableInitializer(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	// ---------------- Start initialisation

	/**
	 * Register the service which is enable to transform Class to Pojo Bindable
	 * Class.
	 */
	@Override
	protected void initialize(BindableStrategy bindableStrategy) {
		// Force loading of classes used to log Bindable before publishing the
		// services
		// to avoid having java.lang.ClassCircularityError when the service
		// OSGiBindableClassTransformer will be consummed.
		forceLoadingClassesToAvoidClassCircularityError();

		// Create service instance which is enable to transform Class to Pojo
		// Bindable Class
		registerService();
	}

	protected void registerService() {
		// Register the service into OSGi registry services.
		this.serviceRegistration = bundleContext.registerService(
				getClassTransformerServiceName(), this, null);
	}

	/**
	 * When service {@link OSGiBindableClassTransformer} is consummed by a
	 * bundle, Classes used into {@link OSGiBindableClassTransformer} must be
	 * alreeady loaded, otherwise java.lang.ClassCircularityError is throwed.
	 * 
	 * This method force using of Classes used into OSGiBindableClassTransformer
	 * to force loading classes and avoid having
	 * java.lang.ClassCircularityError.
	 */
	protected void forceLoadingClassesToAvoidClassCircularityError() {
		// Call Policy class to force to load the class to avoid having
		// java.lang.ClassCircularityError when service
		// OSGiBindableClassTransformer is consumed :
		// 
		// java.lang.ClassCircularityError:
		// org/eclipse/core/databinding/pojo/bindable/internal/logs/Policy
		// at
		// org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy.log(DefaultBindableStrategy.java:121)
		Policy.getLog();

		// Create Dummy status to avoid having :

		// java.lang.ClassCircularityError: org/eclipse/core/runtime/Status
		// at
		// org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy.log(DefaultBindableStrategy.java:122)

		new Status(IStatus.OK, BindableStrategy.POJO_BINDABLE, IStatus.OK, "",
				null);

	}

	@Override
	protected String getHeaderTrace() {
		return "OSGi Bindable with configuration: ";
	}

	/**
	 * Load BindableStrategy instance from the file bindable.properties wich is
	 * stored into an OSGi fragment.
	 * 
	 * @return
	 */
	protected OSGiBindableStrategy loadBindableStrategyFromBindablePropertiesFile() {
		return (OSGiBindableStrategy) loadBindableStrategyFromBindablePropertiesFile(false);
	}

	/**
	 * Returns instance of {@link OSGiBindableStrategy} by loading
	 * bindable.properties file.
	 */
	public OSGiBindableStrategy getBindableStrategy() {
		if (bindableStrategy == null) {
			this.bindableStrategy = loadBindableStrategyFromBindablePropertiesFile();
		}
		return bindableStrategy;
	}

	@Override
	protected DefaultBindableStrategy createBindableStrategy(
			String[] splittedPackages, boolean slashIt) {
		// override this method to create OSGi Bindable Strategy
		return new OSGiDefaultBindableStrategy(splittedPackages, slashIt);
	}

	@Override
	protected void intializeOtherProperties(BindableStrategy bindableStrategy,
			Properties bindableProps) {
		OSGiDefaultBindableStrategy strategy = (OSGiDefaultBindableStrategy) bindableStrategy;

		// Initialize bundes to include coming from "bindable.include_bundles"
		// property.
		String includeBundles = bindableProps
				.getProperty(BINDABLE_INCLUDE_BUNDLES);
		if (!StringUtils.isEmpty(includeBundles)) {
			String[] includeBundlesSplitted = includeBundles.split(";");
			strategy.setIncludeBundles(includeBundlesSplitted);
		}
	}

	@Override
	protected StringBuffer buildTrace(BindableStrategy bindableStrategy) {
		StringBuffer message = super.buildTrace(bindableStrategy);

		// Display "bindable.include_bundles" property
		Collection<String> includeBundles = ((OSGiBindableStrategy) bindableStrategy)
				.getIncludeBundles();
		addParams(message, BINDABLE_INCLUDE_BUNDLES, includeBundles);

		return message;
	}

	// ---------------- Stop initialisation

	/**
	 * Un-Register the service which is enable to transform Class to Pojo
	 * Bindable Class.
	 */
	public void stop() {
		if (serviceRegistration != null) {
			ServiceReference serviceReference = serviceRegistration
					.getReference();
			if (serviceReference != null) {
				bundleContext.ungetService(serviceReference);
			}
		}
	}

	/**
	 * Returns the service name of the service
	 * {@link OSGiBindableClassTransformer}. The service name used (generally)
	 * an interface name.
	 * 
	 * @return
	 */
	protected abstract String getClassTransformerServiceName();
}
