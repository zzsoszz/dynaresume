/*******************************************************************************
 * Copyright (c) 2009 Martin Lippert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Martin Lippert                   initial implementation      
 *   Angelo ZERR                      manage springweaver into Jpa context
 *******************************************************************************/
package org.eclipse.equinox.weaving.springweaver;

import java.lang.instrument.ClassFileTransformer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;

/**
 * Registry of {@link ClassFileTransformer} wich manage the 2 scopes
 * {@link WeaverScope#APPLICATION} and {@link WeaverScope#BUNDLE}.
 * 
 */
public class ClassFileTransformerRegistry {

	private static final ClassFileTransformerWrapper[] EMPTY_CLASS_FILE_TRANSFORMER = new ClassFileTransformerWrapper[0];

	/**
	 * Store APPLICATION scope ClassFileTransformer
	 */
	private List<ClassFileTransformerWrapper> applicationTransformers;

	/**
	 * Store BUNDLE scope ClassFileTransformer
	 */
	private Map<Bundle, BundleClassFileTransformers> bundleTransformers;

	public ClassFileTransformerRegistry() {
		this.applicationTransformers = new ArrayList<ClassFileTransformerWrapper>();
		this.bundleTransformers = new HashMap<Bundle, BundleClassFileTransformers>();
	}

	/**
	 * Add {@link ClassFileTransformer} transformer into this registry.
	 * 
	 * @param importDynamicPackages
	 * 
	 * @param weaverScope
	 *            weaver scope.
	 * @param bundle
	 *            bundle used if {@link WeaverScope} is
	 *            {@link WeaverScope#BUNDLE}.
	 * @param transformer
	 *            the transformer to register.
	 */
	public void addTransformer(WeaverScope weaverScope, Bundle bundle,
			ClassFileTransformer transformer, String importDynamicPackages) {

		switch (weaverScope) {
		case APPLICATION:
			// Application Scope
			this.applicationTransformers.add(new ClassFileTransformerWrapper(
					transformer, importDynamicPackages));
			break;
		case BUNDLE:
			BundleClassFileTransformers concreteTransformers = this.bundleTransformers
					.get(bundle);
			if (concreteTransformers == null) {
				concreteTransformers = new BundleClassFileTransformers();
				this.bundleTransformers.put(bundle, concreteTransformers);
			}

			concreteTransformers.add(new ClassFileTransformerWrapper(
					transformer, importDynamicPackages));
			break;
		}

	}

	/**
	 * Returns list of {@link ClassFileTransformer} to execute for the bundle.
	 * 
	 * @param bundle
	 * @return
	 */
	public ClassFileTransformerWrapper[] getTransformers(Bundle bundle) {
		BundleClassFileTransformers result = this.bundleTransformers
				.get(bundle);
		if (result != null) {
			// It exsit ClassFileTransformer for the bundle
			// Merge it with ClassFileTransformer with scope APPLICATION and
			// return it.
			result.mergeIfNeeded(this.applicationTransformers);
			return (ClassFileTransformerWrapper[]) result
					.toArray(EMPTY_CLASS_FILE_TRANSFORMER);
		} else {
			// return ClassFileTransformer with scope APPLICATION
			return applicationTransformers
					.toArray(EMPTY_CLASS_FILE_TRANSFORMER);
		}
	}

	/**
	 * Store list of {@link ClassFileTransformer} linked to a {@link Bundle}.
	 * 
	 */
	private class BundleClassFileTransformers extends
			ArrayList<ClassFileTransformerWrapper> {

		private static final long serialVersionUID = 195726735267829321L;

		private boolean merged = false;

		/**
		 * Merge {@link ClassFileTransformer} APPLICATION Scope if needed.s
		 * 
		 * @param applicationTransformers
		 */
		public void mergeIfNeeded(
				List<ClassFileTransformerWrapper> applicationTransformers) {
			if (merged)
				return;
			super.addAll(applicationTransformers);
			merged = true;

		}
	}

}
