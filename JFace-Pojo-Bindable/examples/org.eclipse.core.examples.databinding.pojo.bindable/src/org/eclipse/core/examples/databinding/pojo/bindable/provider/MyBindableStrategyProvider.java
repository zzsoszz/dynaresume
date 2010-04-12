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
package org.eclipse.core.examples.databinding.pojo.bindable.provider;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy;

/**
 * {@link BindableStrategyProvider} to configure (package names....) Bindable
 * Java Agent with Java code.
 * 
 * Call the program with JVM parameters :
 * 
 * -javaagent:lib/org.eclipse.core.databinding.pojo.bindable_1.0.0.jar
 * -Dbindable.strategy_provider=org.eclipse.core.examples.databinding.pojo.bindable.provider.MyBindableStrategyProvider
 * 
 * See launch launch/HelloWorldWithBeansObservablesWithBindableAgentProvider.launch
 */
public class MyBindableStrategyProvider implements BindableStrategyProvider {

	private DefaultBindableStrategy bindableStrategy = null;

	public MyBindableStrategyProvider() {

		bindableStrategy = new DefaultBindableStrategy(null);
		bindableStrategy
				.addPackageName("org.eclipse.core.examples.databinding.pojo.bindable.model");
		bindableStrategy.setDebugMode(true);
		// bindableStrategy.setGenBaseDir("D://tmp");
		bindableStrategy.setUseAnnotation(false);
	}

	public BindableStrategy getBindableStrategy() {
		return bindableStrategy;
	}

}
