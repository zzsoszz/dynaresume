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
package org.eclipse.core.databinding.pojo.bindable.internal.equinox;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.equinox.weaving.BindableWeaver;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableClassTransformer;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableInitializer;
import org.osgi.framework.BundleContext;

/**
 * 
 * {@link OSGiBindableInitializer} implementation which use
 * {@link BindableAware} :
 * 
 * <ul>
 * <li>the service {@link OSGiBindableClassTransformer} implements
 * {@link BindableAware} interface.</li>
 * <li>the service is registered into OSGi regsitry services with
 * {@link BindableAware} interface name.</li>
 * </ul>
 */
public class EquinoxBindableInitializer extends OSGiBindableInitializer {

	public EquinoxBindableInitializer(BundleContext bundleContext) {
		super(bundleContext);
	}

	@Override
	protected OSGiBindableClassTransformer createClassTransformerService(
			BindableStrategyProvider provider) {
		return new EquinoxBindableWeaver(provider);
	}

	@Override
	protected String getClassTransformerServiceName() {
		return BindableWeaver.class.getName();
	}

}
