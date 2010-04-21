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
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableStrategy;
import org.osgi.framework.Bundle;

/**
 * {@link OSGiBindableClassTransformer} implementation which implements
 * interface {@link BindableWeaver}.
 */
public class EquinoxBindableWeaver extends OSGiBindableClassTransformer
		implements BindableWeaver {

	public EquinoxBindableWeaver(BindableStrategyProvider provider) {
		super(provider);
	}

	@Override
	public byte[] transform(Bundle bundle, String className,
			byte[] classfileBuffer) {
		if (isBindableBundle(bundle))
			return super.transform(className, classfileBuffer);
		return null;
	}

	/**
	 * Return true if Classes of this Bundle must be transformed and false otherwise.
	 * 
	 * @param bundle
	 * @return
	 */
	private boolean isBindableBundle(Bundle bundle) {
		OSGiBindableStrategy bindableStrategy = (OSGiBindableStrategy) getBindableStrategy();
		if (bindableStrategy == null)
			return false;
		return bindableStrategy.isBindableBundle(bundle);
	}

}
