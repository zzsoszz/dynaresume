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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy;
import org.osgi.framework.Bundle;

/**
 * OSGi Default implementation of {@link OSGiBindableStrategy}
 * 
 */
public class OSGiDefaultBindableStrategy extends DefaultBindableStrategy
		implements OSGiBindableStrategy {

	private Collection<String> includeBundles = null;

	private Map<Bundle, Boolean> includeBundlesCache = null;

	public OSGiDefaultBindableStrategy(String[] packageNames, boolean slashIt) {
		super(packageNames, slashIt);
	}

	public boolean isBindableBundle(Bundle bundle) {
		if (includeBundles == null)
			return true;
		if (includeBundlesCache == null) {
			includeBundlesCache = new HashMap<Bundle, Boolean>();
		}
		Boolean result = includeBundlesCache.get(bundle);
		if (result != null)
			return result.booleanValue();
		boolean bindableBundle = includeBundles.contains(bundle
				.getSymbolicName());
		includeBundlesCache.put(bundle, bindableBundle);
		return bindableBundle;

	}

	public void setIncludeBundles(String[] bundles) {
		includeBundles = new ArrayList<String>();
		for (int i = 0; i < bundles.length; i++) {
			includeBundles.add(bundles[i]);
		}
	}

	public Collection<String> getIncludeBundles() {
		if (includeBundles == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableCollection(includeBundles);
	}
}
