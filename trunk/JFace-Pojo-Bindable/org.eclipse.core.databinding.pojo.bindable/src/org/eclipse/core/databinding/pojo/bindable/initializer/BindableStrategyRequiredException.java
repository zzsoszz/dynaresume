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

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;

/**
 * 
 * Exception throwed when {@link BindableStrategy} instance is null.
 * 
 */
public class BindableStrategyRequiredException extends RuntimeException {

	private static final long serialVersionUID = -6409599787336699402L;

	private static final String MESSAGE = "BindableStrategy is required.";

	public BindableStrategyRequiredException(String message) {
		super(message);
	}

	public BindableStrategyRequiredException() {
		super(MESSAGE);
	}
}
