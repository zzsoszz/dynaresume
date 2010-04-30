/*******************************************************************************
 * Copyright (c) 2009 Martin Lippert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Martin Lippert                   initial implementation      
 *******************************************************************************/
package org.eclipse.equinox.weaving.springweaver;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Map;

import org.eclipse.equinox.service.weaving.IWeavingService;
import org.eclipse.osgi.internal.baseadaptor.DefaultClassLoader;
import org.eclipse.osgi.internal.composite.CompositeClassLoader;
import org.eclipse.osgi.internal.loader.BundleLoader;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

/**
 * Springweaver {@link IWeavingService} implementation.
 * 
 */
public class WeavingService implements IWeavingService {

	private final ClassFileTransformerRegistry registry;
	private final Bundle bundle;
	/**
	 * true if one class of the bundle was woven and false otherwise.
	 */
	private boolean oneClassWasWoven = false;

	public WeavingService(final Bundle bundle,
			ClassFileTransformerRegistry registry) {
		this.bundle = bundle;
		this.registry = registry;
	}

	/**
	 * @see org.eclipse.equinox.service.weaving.IWeavingService#flushGeneratedClasses(java.lang.ClassLoader)
	 */
	public void flushGeneratedClasses(final ClassLoader loader) {
	}

	/**
	 * @see org.eclipse.equinox.service.weaving.IWeavingService#generatedClassesExistFor(java.lang.ClassLoader,
	 *      java.lang.String)
	 */
	public boolean generatedClassesExistFor(final ClassLoader loader,
			final String className) {
		return false;
	}

	/**
	 * @see org.eclipse.equinox.service.weaving.IWeavingService#getGeneratedClassesFor(java.lang.String)
	 */
	public Map<String, byte[]> getGeneratedClassesFor(final String className) {
		return null;
	}

	/**
	 * @see org.eclipse.equinox.service.weaving.IWeavingService#getKey()
	 */
	public String getKey() {
		return "spring";
	}

	/**
	 * @see org.eclipse.equinox.service.weaving.IWeavingService#preProcess(java.lang.String,
	 *      byte[], java.lang.ClassLoader)
	 */
	public byte[] preProcess(final String name, final byte[] classbytes,
			final ClassLoader loader) throws IOException {

		byte[] result = null;

		ClassFileTransformerWrapper transformer = null;
		ClassFileTransformerWrapper[] transformers = this.registry
				.getTransformers(this.bundle);
		for (int i = 0; i < transformers.length; i++) {
			try {
				transformer = transformers[i];
				result = transformer.transform(loader, name, null, null,
						classbytes);
				importDynamicPackagesIfNeeded(transformer, result, loader);
			} catch (IllegalClassFormatException e) {
				e.printStackTrace();
			}
		}
		if (result != null) {
			oneClassWasWoven = true;
		}
		return result;
	}

	private void importDynamicPackagesIfNeeded(
			final ClassFileTransformerWrapper transformer,
			final byte[] newBytes, final ClassLoader loader) {

		if (newBytes == null)
			return;

		if (oneClassWasWoven)
			return;
		String packages = transformer.getDynamicImportPackages();
		if (packages == null && packages.length() < 1)
			return;

		BundleLoader bundleLoader = getBundleLoader(loader);
		if (bundleLoader != null) {
			try {
				bundleLoader
						.addDynamicImportPackage(ManifestElement.parseHeader(
								Constants.DYNAMICIMPORT_PACKAGE, packages));
			} catch (BundleException e) {
				// TODO : which exception to throw????
				e.printStackTrace();
			}
		}

	}

	private BundleLoader getBundleLoader(ClassLoader loader) {
		if (loader instanceof DefaultClassLoader) {
			return (BundleLoader) ((DefaultClassLoader) loader).getDelegate();
		}
		if (loader instanceof CompositeClassLoader) {
			return (BundleLoader) ((CompositeClassLoader) loader).getDelegate();
		}
		return null;
	}

}
