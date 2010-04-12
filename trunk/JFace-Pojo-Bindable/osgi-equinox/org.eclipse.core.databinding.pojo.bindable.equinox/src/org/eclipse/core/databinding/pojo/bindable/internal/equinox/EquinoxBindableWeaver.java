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

import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.equinox.weaving.BindableWeaver;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableClassTransformer;

/**
 * {@link OSGiBindableClassTransformer} implementation which implements
 * interface {@link BindableWeaver}.
 */
public class EquinoxBindableWeaver extends OSGiBindableClassTransformer
		implements BindableWeaver {

	public EquinoxBindableWeaver(BindableStrategyProvider provider) {
		super(provider);
	}

}
