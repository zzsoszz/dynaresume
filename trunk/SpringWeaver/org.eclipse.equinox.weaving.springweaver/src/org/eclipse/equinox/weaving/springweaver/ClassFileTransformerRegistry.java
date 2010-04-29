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

	private static final ClassFileTransformer[] EMPTY_CLASS_FILE_TRANSFORMER = new ClassFileTransformer[0];

	/**
	 * Store APPLICATION scope ClassFileTransformer
	 */
	private List<ClassFileTransformer> applicationTransformers;

	/**
	 * Store BUNDLE scope ClassFileTransformer
	 */
	private Map<Bundle, BundleClassFileTransformers> bundleTransformers;

	public ClassFileTransformerRegistry() {
		this.applicationTransformers = new ArrayList<ClassFileTransformer>();
		this.bundleTransformers = new HashMap<Bundle, BundleClassFileTransformers>();
	}

	/**
	 * Add {@link ClassFileTransformer} transformer into this registry.
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
			ClassFileTransformer transformer) {

		switch (weaverScope) {
		case APPLICATION:
			// Application Scope
			this.applicationTransformers.add(transformer);
			break;
		case BUNDLE:
			BundleClassFileTransformers concreteTransformers = this.bundleTransformers
					.get(bundle);
			if (concreteTransformers == null) {
				concreteTransformers = new BundleClassFileTransformers();
				this.bundleTransformers.put(bundle, concreteTransformers);
			}

			concreteTransformers.add(transformer);
			break;
		}

	}

	/**
	 * Returns list of {@link ClassFileTransformer} to execute for the bundle.
	 * 
	 * @param bundle
	 * @return
	 */
	public ClassFileTransformer[] getTransformers(Bundle bundle) {
		BundleClassFileTransformers result = this.bundleTransformers
				.get(bundle);
		if (result != null) {
			// It exsit ClassFileTransformer for the bundle
			// Merge it with ClassFileTransformer with scope APPLICATION and
			// return it.
			result.mergeIfNeeded(this.applicationTransformers);
			return (ClassFileTransformer[]) result
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
			ArrayList<ClassFileTransformer> {

		private static final long serialVersionUID = 195726735267829321L;

		private boolean merged = false;

		/**
		 * Merge {@link ClassFileTransformer} APPLICATION Scope if needed.s
		 * 
		 * @param applicationTransformers
		 */
		public void mergeIfNeeded(
				List<ClassFileTransformer> applicationTransformers) {
			if (merged)
				return;
			super.addAll(applicationTransformers);
			merged = true;

		}
	}

}
