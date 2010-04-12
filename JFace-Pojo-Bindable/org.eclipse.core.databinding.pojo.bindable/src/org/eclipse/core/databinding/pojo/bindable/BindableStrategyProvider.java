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


/**
 * Interface to provide {@link BindableStrategy}.
 * 
 * This interface must be implemented when {@link BindableInitializerAgent} is called with
 * parameter -Dbindable.strategy_provider=MyBindableStrategyProvider
 * 
 */
public interface BindableStrategyProvider {

	/**
	 * Returns {@link BindableStrategy} instance.
	 * 
	 * @return
	 */
	BindableStrategy getBindableStrategy();
}
