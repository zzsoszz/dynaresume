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

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;

/**
 * 
 * Constants uses to configure {@link BindableStrategy}.
 * 
 */
public interface BindableInitializerConstants {

	String BINDABLE_STRATEGY_PROVIDER = "bindable.strategy_provider";
	String BINDABLE_PACKAGES = "bindable.packages";
	String BINDABLE_USE_ANNOTATION = "bindable.use_annotation";
	String BINDABLE_GEN_BASEDIR = "bindable.gen_basedir";
	String BINDABLE_DEBUG = "bindable.debug";
	
	// OSGi context
	String BINDABLE_INCLUDE_BUNDLES = "bindable.include_bundles";
}
