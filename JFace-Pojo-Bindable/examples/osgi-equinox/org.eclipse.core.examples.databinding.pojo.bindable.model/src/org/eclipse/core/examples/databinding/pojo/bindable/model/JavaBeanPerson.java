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
package org.eclipse.core.examples.databinding.pojo.bindable.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * JavaBean model Person.
 *
 */
public class JavaBeanPerson {

	// A property...
	String name = "HelloWorld";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Object oldValue = this.name;
		Object newValue = name;
		this.name = name;
		firePropertyChange("name", oldValue, newValue);
	}
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName,
				listener);
	}

	
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName,
				listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}
}
