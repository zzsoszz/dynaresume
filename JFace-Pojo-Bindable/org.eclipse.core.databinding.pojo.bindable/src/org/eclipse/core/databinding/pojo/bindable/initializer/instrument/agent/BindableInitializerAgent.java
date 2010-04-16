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
package org.eclipse.core.databinding.pojo.bindable.initializer.instrument.agent;

import static org.eclipse.core.databinding.pojo.bindable.internal.util.StringUtils.isEmpty;
import static org.eclipse.core.databinding.pojo.bindable.internal.util.StringUtils.isTrue;

import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.util.Enumeration;

import org.eclipse.core.databinding.pojo.bindable.BindableHelper;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.eclipse.core.databinding.pojo.bindable.initializer.AbstractBindableInitializer;
import org.eclipse.core.databinding.pojo.bindable.internal.util.ClassUtils;

/**
 * 
 * Java Agent which add {@link BindableClassFileTransformer} into
 * {@link Instrumentation} instance. This agent must be called with 2 parameters
 * into JVM Parameters :
 * 
 * <ul>
 * <li>-Dbindable.strategy_provider=<provider> : to set an implementation of
 * {@link BindableStrategyProvider} to configure {@link BindableStrategy} with
 * Java code.</li>
 * 
 * <li>-Dbindable.packages=<packages> : to set packages of POJO to transform.
 * Use ';' caracter to set several packages.</li>
 * 
 * <li>-Dbindable.use_annotation=<true|false> (Optionnal) : to set if
 * {@link Bindable} annotation must be used or not.</li>
 * <li>-Dbindable.gen_basedir=<path> (Optionnal) : set the path of base dir if
 * you wish generate the result of transformed class into a file.</li>
 * <li>-Dbindable.debug=<true|false> (Optionnal) : if true, display information
 * about {@link BindableInitializerAgent} and Class Transformation.</li>
 * </ul>
 * 
 */
public class BindableInitializerAgent extends AbstractBindableInitializer {

	private static volatile Instrumentation instrumentation;

	private static final BindableInitializerAgent INSTANCE = new BindableInitializerAgent();

	public static void premain(String agentArgs, Instrumentation instrumentation) {

		// Save the Instrumentation instance.
		BindableInitializerAgent.instrumentation = instrumentation;
		INSTANCE.start();

	}

	@Override
	protected void initialize(BindableStrategy bindableStrategy) {
		// Add {@link ClassFileTransformer} for Pojo Bindable into
		// instrumentation by using bindableStrategy.
		BindableHelper.initialize(bindableStrategy,
				BindableInitializerAgent.instrumentation);
	}

	public void stop() {
		// Do noting

	}

	@Override
	protected String getHeaderTrace() {
		return "Java Bindable Agent called, with configuration: ";
	}

	public BindableStrategy getBindableStrategy() {
		// get BindableStrategy instance from -Dbindable.strategy_provider
		BindableStrategy bindableStrategy = getBindableStrategyFromProvider();
		if (bindableStrategy != null)
			return bindableStrategy;
		// get BindableStrategy instance from -Dbindable.packages
		bindableStrategy = getBindableStrategyFromPackages();
		if (bindableStrategy != null)
			return bindableStrategy;
		// get BindableStrategy instance from src/bindable.properties
		return getBindableStrategyBindablePropertiesFile();

	}

	/**
	 * Returns BindableStrategy instance from -Dbindable.strategy_provider
	 * 
	 * @return
	 */
	private BindableStrategy getBindableStrategyFromProvider() {
		// -Dbindable.strategy_provider=<instance of BindableStrategyProvider>
		String bindableStrategyProviderClassName = System
				.getProperty(BINDABLE_STRATEGY_PROVIDER);
		if (!isEmpty(bindableStrategyProviderClassName)) {
			ClassLoader classLoader = BindableInitializerAgent.class
					.getClassLoader();
			try {
				BindableStrategyProvider provider = ClassUtils
						.createBindableStrategyProviderInstance(classLoader,
								bindableStrategyProviderClassName);
				return provider.getBindableStrategy();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Returns BindableStrategy instance from -Dbindable.packages
	 * 
	 * @return
	 */
	private BindableStrategy getBindableStrategyFromPackages() {
		// -Dbindable.packages=<packages name>
		String packages = System.getProperty(BINDABLE_PACKAGES);
		if (isEmpty(packages)) {
			return null;
		}
		
		// -Dbindable.use_annotation=<true|false>
		boolean useAnnotation = isTrue(System
				.getProperty(BINDABLE_USE_ANNOTATION));

		// -Dbindable.gen_basedir=<path of base dir where class transformed must
		// be generated>
		String genBaseDir = System.getProperty(BINDABLE_GEN_BASEDIR);

		// -Dbindable.debug=><true|false>
		boolean debugMode = isTrue(System.getProperty(BINDABLE_DEBUG));

		// Create BindableStrategy and add Bindable ClassFileTransformer to
		// Instrumentation instance.
		return createBindableStrategy(packages, useAnnotation, genBaseDir,
				debugMode, true);
	}

	/**
	 * Returns BindableStrategy instance from src/bindable.properties
	 * 
	 * @return
	 */
	private BindableStrategy getBindableStrategyBindablePropertiesFile() {
		return loadBindableStrategyFromBindablePropertiesFile(true);
	}

	/**
	 * Returns instance of {@link Instrumentation} getted from premain method.
	 * 
	 * @return
	 */
	public static Instrumentation getInstrumentation() {
		return instrumentation;
	}
}
