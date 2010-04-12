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
package org.eclipse.core.databinding.pojo.bindable.initializer.instrument;

import java.lang.instrument.Instrumentation;

import org.springframework.instrument.InstrumentationSavingAgent;

/**
 * 
 * {@link InstrumentationProvider} implementation wich use Spring
 * {@link InstrumentationSavingAgent} which get the instance of
 * {@link Instrumentation} by calling program with -javaagent:spring-agent.jar
 * JVM parameter.
 * 
 */
public class SpringInstrumentationProvider implements InstrumentationProvider {

	public static final InstrumentationProvider INSTANCE = new SpringInstrumentationProvider();

	private SpringInstrumentationProvider() {

	}

	/**
	 * Return the instance of {@link Instrumentation} updated with
	 * -javaagent:spring-agent.jar
	 */
	public Instrumentation getInstrumentation()
			throws UnavailableInstrumentationException {
		Instrumentation instrumentation = InstrumentationSavingAgent
				.getInstrumentation();
		if (instrumentation == null) {
			throw new UnavailableInstrumentationException(
					"Instrumentation is null. Call your program with -javaagent:spring-agent.jar");
		}
		return instrumentation;
	}
}
