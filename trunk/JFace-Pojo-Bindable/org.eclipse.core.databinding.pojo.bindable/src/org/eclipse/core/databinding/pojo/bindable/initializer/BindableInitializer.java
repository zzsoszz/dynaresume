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
package org.eclipse.core.databinding.pojo.bindable.initializer;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.agent.BindableInitializerAgent;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableInitializer;

/**
 * Life-cycle to initialize Pojo Bindable into any environment (Java Agent with
 * {@link BindableInitializerAgent}, OSGi context with
 * {@link OSGiBindableInitializer}).
 * 
 */
public interface BindableInitializer extends BindableStrategyProvider {

	/**
	 * Configure Pojo Bindable.
	 */
	void start();

	/**
	 * Deconfigure Pojo Bindable.
	 */
	void stop();
}
