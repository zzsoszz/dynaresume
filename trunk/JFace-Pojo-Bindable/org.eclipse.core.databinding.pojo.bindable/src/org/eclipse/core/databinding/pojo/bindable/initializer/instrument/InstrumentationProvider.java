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

/**
 * 
 * Interface to provide {@link Instrumentation} instance (which is getted from a
 * Java Agent).
 * 
 */
public interface InstrumentationProvider {

	/**
	 * Return instance of {@link Instrumentation} (which is getted from a Java
	 * Agent).
	 * 
	 * @return
	 */
	Instrumentation getInstrumentation() throws UnavailableInstrumentationException;
}
