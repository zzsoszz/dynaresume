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
import org.eclipse.core.databinding.pojo.bindable.initializer.AbstractBindableClassTransformer;

/**
 * Abstract class for OSGi service wich transform Class to Bindable Class. This
 * class is abstract because Bundle OSGi which wish use this class to publish it
 * into OSGi registry services, must extend this class and implements an
 * interface :
 * 
 * See :
 * 
 * <ul>
 * <li>Bundle org.eclipse.core.internal.databinding.pojo.bindable.equinox where
 * OSGiBindableClassTransformer is implemented with
 * org.eclipse.core.databinding.pojo.bindable.equinox.weaving.BindableWeaver
 * interface.</li>
 * <li>Bundle org.eclipse.persistence.jpa.equinox.bindable where
 * OSGiBindableClassTransformer is implemented with EclipseLink
 * org.eclipse.persistence.jpa.equinox.weaving.IWeaver interface.</li>
 * </ul>
 * 
 */
public abstract class OSGiBindableClassTransformer extends
		AbstractBindableClassTransformer {

	// BindableStrategyProvider is OSGiBindableInitializer which is enable to
	// prepare
	// BindableStrategy from the file bindable.properties from a OSGi Fragment.
	private BindableStrategyProvider provider;

	public OSGiBindableClassTransformer(BindableStrategyProvider provider) {
		this.provider = provider;
	}

	public BindableStrategy getBindableStrategy() {
		return provider.getBindableStrategy();
	}

}
