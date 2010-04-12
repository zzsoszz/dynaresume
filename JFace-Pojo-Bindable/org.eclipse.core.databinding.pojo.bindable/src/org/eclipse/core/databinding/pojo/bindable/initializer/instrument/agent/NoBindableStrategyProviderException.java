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

import static java.text.MessageFormat.format;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.initializer.BindableInitializerConstants;

/**
 * 
 * Exception throwed when "bindable.strategy_provider" parameter is filled with
 * class which implements {@link BindableStrategyProvider}.
 * 
 */
public class NoBindableStrategyProviderException extends RuntimeException
		implements BindableInitializerConstants {

	private static final long serialVersionUID = -6409599787336699402L;

	private static final String MESSAGE = "Class name {0} of parameter {1} doesn't implement <"
			+ BindableStrategyProvider.class.getName() + ">";

	public NoBindableStrategyProviderException(String className) {
		super(format(MESSAGE, className, BINDABLE_STRATEGY_PROVIDER));
	}
}
