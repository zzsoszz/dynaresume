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

import static org.eclipse.core.databinding.pojo.bindable.internal.util.StringUtils.isEmpty;

import java.util.Collection;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.agent.BindableInitializerAgent;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.agent.BindablePackagesRequiredException;
import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableInitializer;
import org.eclipse.core.runtime.IStatus;

/**
 * Base class used in any environnment to initialize Pojo Bindable.
 * 
 * @see {@link BindableInitializerAgent}
 * @see {@link OSGiBindableInitializer}.
 */
public abstract class AbstractBindableInitializer implements
		BindableInitializer, BindableInitializerConstants {

	/**
	 * Configure Pojo Bindable.
	 */
	public void start() {
		// Get BindableStrategy instance
		BindableStrategy bindableStrategy = getBindableStrategy();

		// Check if BindableStrategy instance is not null.
		checkRequiredBindableStrategy(bindableStrategy);

		// Initialize Pojo Bindable switch the environnment (Java Agent, OSGi
		// context...) by using BindableStrategy instance.
		initialize(bindableStrategy);

		// Trace information about BindableStrategy instance (if debugMode).
		trace(bindableStrategy);
	}

	/**
	 * Throw {@link BindableStrategyRequiredException} if bindableStrategy is
	 * null.
	 * 
	 * @param bindableStrategy
	 */
	protected void checkRequiredBindableStrategy(
			BindableStrategy bindableStrategy) {
		if (bindableStrategy == null) {
			throw new BindableStrategyRequiredException();
		}

	}

	/**
	 * Create instance of {@link BindableStrategy} and configure it.
	 * 
	 * @param packages
	 * @param useAnnotation
	 * @param genBaseDir
	 * @param debugMode
	 * @param slashIt
	 * @return
	 */
	protected BindableStrategy createBindableStrategy(String packages,
			boolean useAnnotation, String genBaseDir, boolean debugMode,
			boolean slashIt) {
		// -Dbindable.packages=<packages name>
		if (isEmpty(packages)) {
			throw new BindablePackagesRequiredException();
		}
		String[] splittedPackages = packages.split(";");

		// Create BindableStrategy and add Bindable ClassFileTransformer to
		// Instrumentation instance.
		DefaultBindableStrategy bindableStrategy = createBindableStrategy(
				splittedPackages, slashIt);
		bindableStrategy.setUseAnnotation(useAnnotation);
		bindableStrategy.setGenBaseDir(genBaseDir);
		bindableStrategy.setDebugMode(debugMode);

		return bindableStrategy;
	}

	/**
	 * Create instance of {@link DefaultBindableStrategy}.
	 * 
	 * @param splittedPackages
	 * @param slashIt
	 * @return
	 */
	protected DefaultBindableStrategy createBindableStrategy(
			String[] splittedPackages, boolean slashIt) {
		return new DefaultBindableStrategy(splittedPackages, slashIt);
	}

	/**
	 * Trace information about BindableStrategy instance (if debugMode).
	 * 
	 * @param bindableStrategy
	 */
	private void trace(BindableStrategy bindableStrategy) {
		boolean debugMode = bindableStrategy.isDebugMode();
		if (debugMode) {
			// Debug mode, print info about BindableStrategy

			StringBuffer message = new StringBuffer();
			message.append(getHeaderTrace());
			addParam(message, BINDABLE_PACKAGES, "");
			int p = 0;
			Collection<String> packageNames = bindableStrategy
					.getPackageNames();
			for (String packageName : packageNames) {
				if (p > 0) {
					message.append("\n");
					for (int i = 0; i < BINDABLE_PACKAGES.length() + 1; i++) {
						message.append(" ");
					}
				}
				message.append(packageName);
				p++;
			}
			boolean useAnnotation = bindableStrategy.isUseAnnotation();
			addParam(message, BINDABLE_USE_ANNOTATION, useAnnotation);
			addParam(message, BINDABLE_DEBUG, debugMode);
			String genBaseDir = bindableStrategy.getGenBaseDir();
			addParam(message, BINDABLE_GEN_BASEDIR, genBaseDir);
			addParam(message, BINDABLE_STRATEGY_PROVIDER, System
					.getProperty(BINDABLE_STRATEGY_PROVIDER));

			// Trace
			bindableStrategy.log(IStatus.INFO, message.toString(), null);
		}

	}

	/**
	 * Add parameter into message.
	 * 
	 * @param message
	 * @param paramName
	 * @param paramValue
	 */
	private void addParam(StringBuffer message, String paramName,
			Object paramValue) {
		message.append("\n");
		message.append(paramName);
		message.append("=");
		message.append(paramValue);
	}

	/**
	 * Initialize Pojo Bindable by using BindableStrategy information.
	 * 
	 * @param bindableStrategy
	 */
	protected abstract void initialize(BindableStrategy bindableStrategy);

	/**
	 * Return the Header to display into log when BindableStrategy is traced.
	 * 
	 * @return
	 */
	protected abstract String getHeaderTrace();

}
