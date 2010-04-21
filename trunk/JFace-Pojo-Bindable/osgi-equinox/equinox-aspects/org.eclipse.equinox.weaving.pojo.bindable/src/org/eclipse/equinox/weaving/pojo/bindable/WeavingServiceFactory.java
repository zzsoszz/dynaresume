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
package org.eclipse.equinox.weaving.pojo.bindable;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableInitializer;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableStrategy;
import org.eclipse.equinox.service.weaving.ISupplementerRegistry;
import org.eclipse.equinox.service.weaving.IWeavingService;
import org.eclipse.equinox.service.weaving.IWeavingServiceFactory;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.State;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * 
 * {@link IWeavingServiceFactory} implementation which load
 * {@link BindableStrategy} and returns {@link IWeavingService} wich use this
 * {@link BindableStrategy} to know if Classes must be transformed or not.
 * 
 */
public class WeavingServiceFactory extends OSGiBindableInitializer implements
		IWeavingServiceFactory {

	private IWeavingService weavingService = null;

	public WeavingServiceFactory(BundleContext bundleContext) {
		super(bundleContext);
		this.weavingService = new WeavingService(this);
	}

	/**
	 * @see org.eclipse.equinox.service.weaving.IWeavingServiceFactory#createWeavingService(java.lang.ClassLoader,
	 *      org.osgi.framework.Bundle, org.eclipse.osgi.service.resolver.State,
	 *      org.eclipse.osgi.service.resolver.BundleDescription,
	 *      org.eclipse.equinox.service.weaving.ISupplementerRegistry)
	 */
	public IWeavingService createWeavingService(final ClassLoader loader,
			final Bundle bundle, final State resolverState,
			final BundleDescription bundleDesciption,
			final ISupplementerRegistry supplementerRegistry) {
		// Test if classes of the Bundle can be transformed with Pojo Bindable.
		if (isBindableBundle(bundle))
			// Classes of the Bundle can be transformed, returns the
			// IWeavingService instance linked to the BindableStrategy.
			return weavingService;
		return null;
	}

	@Override
	protected String getClassTransformerServiceName() {
		return IWeavingServiceFactory.class.getName();
	}

	/**
	 * Return true if Classes of this Bundle must be transformed and false
	 * otherwise.
	 * 
	 * @param bundle
	 * @return
	 */
	private boolean isBindableBundle(Bundle bundle) {
		OSGiBindableStrategy bindableStrategy = getBindableStrategy();
		if (bindableStrategy == null)
			return false;
		return bindableStrategy.isBindableBundle(bundle);
	}

}
