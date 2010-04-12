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

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.InstrumentationProvider;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.UnavailableInstrumentationException;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.agent.BindableClassFileTransformer;

/**
 * Helper to configure Instrumentation with {@link BindableStrategy}. This
 * initializer MUST BE called BEFORE loading a POJO Class.
 * 
 */
public class BindableHelper {

	/**
	 * Configure Instrumentation with {@link BindableStrategy}
	 * 
	 * @param bindableStrategy
	 */
	public static void initialize(BindableStrategy bindableStrategy,
			InstrumentationProvider instrumentationProvider) {

		// Get Instrumentation instance
		Instrumentation instrumentation = instrumentationProvider
				.getInstrumentation();
		initialize(bindableStrategy, instrumentation);
	}

	/**
	 * Add {@link ClassFileTransformer} for Pojo Bindable into instrumentation
	 * by using bindableStrategy.
	 * 
	 * @param bindableStrategy
	 * @param instrumentation
	 */
	public static void initialize(BindableStrategy bindableStrategy,
			Instrumentation instrumentation) {
		if (instrumentation == null) {
			throw new UnavailableInstrumentationException(
					"Instrumentation is not available.");
		}

		// Create ClassFileTransformer
		ClassFileTransformer classFileTransformerBindable = new BindableClassFileTransformer(
				bindableStrategy);

		// Add it to Instrmentation instance.
		instrumentation.addTransformer(classFileTransformerBindable);
	}

	/**
	 * Return true if object implements {@link BindableAware} interface and
	 * false otherwise.
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isBindableAware(Object o) {
		return (o instanceof BindableAware);
	}

	/**
	 * Cast the object to {@link BindableAware} interface if possible.
	 * 
	 * @param o
	 * @return
	 */
	public static BindableAware toBindableAware(Object o) {
		if (isBindableAware(o))
			throw new RuntimeException("Object " + o + " doesn't implements "
					+ BindableAware.class.getName());
		return ((BindableAware) o);
	}

}
