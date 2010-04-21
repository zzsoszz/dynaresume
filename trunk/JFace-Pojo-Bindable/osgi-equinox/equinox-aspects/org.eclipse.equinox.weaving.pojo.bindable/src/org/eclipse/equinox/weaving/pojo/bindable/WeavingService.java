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

import java.io.IOException;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableClassTransformer;
import org.eclipse.equinox.service.weaving.IWeavingService;

/**
 * 
 * {@link IWeavingService} implementation to transform Class to Pojo bindable.
 * 
 */
public class WeavingService extends OSGiBindableClassTransformer implements
		IWeavingService {

	private static final String POJO_BINDABLE_KEY = "Pojo-Bindable";

	public WeavingService(BindableStrategyProvider provider) {
		super(provider);
	}

	public void flushGeneratedClasses(ClassLoader loader) {
	}

	public boolean generatedClassesExistFor(ClassLoader loader, String className) {
		return false;
	}

	public String getKey() {
		return POJO_BINDABLE_KEY;
	}

	public byte[] preProcess(String name, byte[] classbytes, ClassLoader loader)
			throws IOException {
		return super.transform(name, classbytes);
	}

}
