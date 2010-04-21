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

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * OSGi Bindable Initializer which register a service
 * {@link OSGiBindableClassTransformer} into OSGi registry services which is
 * enable to transform Class into Pojo Bindable. This initializer must be
 * created into another bundle :
 * 
 * <ul>
 * <li>{@link OSGiBindableClassTransformerInitializer#start()} must be called
 * into {@link BundleActivator#start(BundleContext)}.</li>
 * <li>{@link OSGiBindableClassTransformerInitializer#stop()} must be called
 * into {@link BundleActivator#stop(BundleContext)}.</li>
 * </ul>
 * 
 * {@link BindableStrategy} is built by using "bindable.properties" file comming
 * from a OSGi fragment attached to the bundle which create an instance of
 * {@link OSGiBindableInitializer}.
 */
public abstract class OSGiBindableClassTransformerInitializer extends
		OSGiBindableInitializer {

	public OSGiBindableClassTransformerInitializer(BundleContext bundleContext) {
		super(bundleContext);
	}

	@Override
	protected void registerService() {
		// Register the service OSGiBindableClassTransformer into OSGi registry
		// services.
		OSGiBindableClassTransformer service = createClassTransformerService(this);
		this.serviceRegistration = bundleContext.registerService(
				getClassTransformerServiceName(), service, null);

	}

	/**
	 * Create instance of the service {@link OSGiBindableClassTransformer}. This
	 * service must implements an interface wich is used too into
	 * {@link OSGiBindableInitializer#getClassTransformerServiceName()} as
	 * service name.
	 * 
	 * @param bindableStrategy
	 * @return
	 */
	protected abstract OSGiBindableClassTransformer createClassTransformerService(
			BindableStrategyProvider bindableStrategy);

}
