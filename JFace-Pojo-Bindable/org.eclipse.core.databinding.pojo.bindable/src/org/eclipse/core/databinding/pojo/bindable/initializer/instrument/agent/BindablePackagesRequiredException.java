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

import org.eclipse.core.databinding.pojo.bindable.initializer.BindableInitializerConstants;

/**
 * 
 * Exception throwed when "bindable.packages" parameter is not founded into JVM
 * Parameter when {@link BindableInitializerAgent} is used.
 * 
 */
public class BindablePackagesRequiredException extends RuntimeException
		implements BindableInitializerConstants {

	private static final long serialVersionUID = -6409599787336699402L;

	private static final String MESSAGE = "Parameter {0} not found into JVM argument (ex : -D{0}=com.example.domain)";

	public BindablePackagesRequiredException() {
		super(format(MESSAGE, BINDABLE_PACKAGES));
	}
}
