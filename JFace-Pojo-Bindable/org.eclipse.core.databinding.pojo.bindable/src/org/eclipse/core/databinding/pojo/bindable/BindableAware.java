/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *     Joga Singh <joga.singh@gmail.com> Added addPropertyChangeListener and removePropertyChangeListener methods with only one argument.
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable;

import java.beans.PropertyChangeListener;

/**
 * Interface added to the POJO class transformed. This method follow the
 * signature of the methods used with JFace Beans Databinding to add/remove
 * {@link PropertyChangeListener}.
 * 
 */
public interface BindableAware {

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property. The same listener object may be added more than once. For each
	 * property, the listener will be invoked the number of times it was added
	 * for that property. If <code>propertyName</code> or <code>listener</code>
	 * is null, no exception is thrown and no action is taken.
	 * 
	 * @param propertyName
	 *            The name of the property to listen on.
	 * @param listener
	 *            The PropertyChangeListener to be added
	 */
	void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);
	
	/**
	 * Add a PropertyChangeListener for all properties. If <code>listener</code>
	 * is null, no exception is thrown and no action is taken.
	 * 
	 * @param listener
	 *            The PropertyChangeListener to be added
	 */	
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a PropertyChangeListener for a specific property. If
	 * <code>listener</code> was added more than once to the same event source
	 * for the specified property, it will be notified one less time after being
	 * removed. If <code>propertyName</code> is null, no exception is thrown and
	 * no action is taken. If <code>listener</code> is null, or was never added
	 * for the specified property, no exception is thrown and no action is
	 * taken.
	 * 
	 * @param propertyName
	 *            The name of the property that was listened on.
	 * @param listener
	 *            The PropertyChangeListener to be removed
	 */

	void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * Remove a PropertyChangeListene. If <code>listener</code> is null, or was never added, 
	 * no exception is thrown and no action is taken.
	 * 
	 * @param listener
	 *            The PropertyChangeListener to be removed
	 */
	
	void removePropertyChangeListener(PropertyChangeListener listener);
}
