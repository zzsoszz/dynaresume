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
package org.eclipse.core.databinding.pojo.bindable.initializer.osgi;

import java.beans.PropertyChangeSupport;
import java.util.Collection;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.osgi.framework.Bundle;

/**
 * {@link BindableStrategy} for OSGi context to know if Classes of a Bundle must
 * be transformed or not.
 * 
 */
public interface OSGiBindableStrategy extends BindableStrategy {

	/**
	 * Return true if classes of OSGi Bundle must be transformed (by calling
	 * {@link PropertyChangeSupport#firePropertyChange(java.beans.PropertyChangeEvent)}
	 * and false otherwise.
	 * 
	 * @param bundle
	 * @return
	 */
	boolean isBindableBundle(Bundle bundle);

	/**
	 * Returns list of bundle to include to transform their class.
	 * 
	 * @return
	 */
	Collection<String> getIncludeBundles();
}
