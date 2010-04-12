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
package org.eclipse.core.databinding.pojo.bindable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.eclipse.core.databinding.pojo.bindable.internal.util.ClassUtils;
import org.eclipse.core.databinding.pojo.bindable.logs.Policy;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * 
 * Default implementation of {@link BindableStrategy} which :
 * 
 * <ul>
 * <li>use package name to know if Class must be transformed or not. See
 * {@link BindableStrategy#isBindableClass(String)}</li>
 * <li>transform all the method starting with set* of classes to transform.</li>
 * </ul>
 * .
 * 
 */
public class DefaultBindableStrategy implements BindableStrategy {

	// Collection of package name (with slash)which contains class to transform.
	private Collection<String> packageNamesWithSlash = null;

	// true if use Bindable annotation and false otherwise
	private boolean useAnnotation = false;

	private String genBaseDir = null;

	private boolean debugMode;

	private boolean slashIt = true;

	public DefaultBindableStrategy(String[] packageNames) {
		this(packageNames, true);
	}

	/**
	 * Constructor to configure array of package names which contains class to
	 * transform. Here {@link Bindable} annotation is not available.
	 * 
	 * @param packageNames
	 */
	public DefaultBindableStrategy(String[] packageNames, boolean slashIt) {
		this.packageNamesWithSlash = (packageNames != null ? ClassUtils
				.toSlash(packageNames, slashIt) : null);
		this.useAnnotation = false;
		this.genBaseDir = null;
		this.debugMode = false;
		this.slashIt = slashIt;
	}

	// ---------------- BindableStrategy interface implementation

	public boolean isBindableClass(String className) {
		// test if className (with '/') is contained into array of packages
		// name.
		if (packageNamesWithSlash == null)
			return false;
		for (String packagesName : packageNamesWithSlash) {
			if (className.startsWith(packagesName))
				return true;
		}
		return false;
	}

	public boolean isBindableMethod(String className, String methodName) {
		// test if methode start with 'set'
		return methodName.startsWith(ClassUtils.SET_PREFIX_METHOD);
	}

	public boolean isUseAnnotation() {
		return useAnnotation;
	}

	public void setUseAnnotation(boolean useAnnotation) {
		this.useAnnotation = useAnnotation;
	}

	public String getGenBaseDir() {
		return genBaseDir;
	}

	public void setGenBaseDir(String genBaseDir) {
		if (genBaseDir != null && !genBaseDir.endsWith("/")) {
			// Add '/' if doesn't exists.
			genBaseDir += "/";
		}
		this.genBaseDir = genBaseDir;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	/**
	 * Logs a message to the Bindable logger.
	 */
	public void log(int severity, String message, Throwable throwable) {
		if (!isDebugMode())
			return;
		Policy.getLog().log(
				new Status(severity, BindableStrategy.POJO_BINDABLE,
						IStatus.OK, message, throwable));
	}

	public Collection<String> getPackageNames() {
		if (packageNamesWithSlash == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableCollection(packageNamesWithSlash);
	}

	public void addPackageName(String packageName) {
		if (packageNamesWithSlash == null) {
			packageNamesWithSlash = new ArrayList<String>();
		}
		packageNamesWithSlash.add(ClassUtils.toSlash(packageName, slashIt));
	}

}
