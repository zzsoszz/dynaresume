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
 * Exception throwed when {@link Instrumentation} instance is not available.
 * 
 */
public class UnavailableInstrumentationException extends RuntimeException {

	private static final long serialVersionUID = -4140998675260448459L;

	public UnavailableInstrumentationException(String message) {
		super(message);
	}
}
