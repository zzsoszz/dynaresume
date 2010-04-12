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
package org.eclipse.core.examples.databinding.pojo.bindable.equinox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import org.eclipse.core.examples.databinding.pojo.bindable.model.PojoPerson;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Basic sample with Pojo Bindable (this bundle doesn't import package of Pojo
 * bindable). When PojoPerson Class is loaded Equinox Hook Adapter (see fragment
 * org.eclipse.core.databinding.pojo.bindable.equinox.weaving or
 * org.eclipse.persistence.jpa.equinox.weaving) change the  bytecode of
 * PojoPerson to add PropertyChangeSupport.
 * 
 */
public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {

		// Create Pojo instance
		PojoPerson person = new PojoPerson();

		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out.println("---------- Property User changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
			}
		};

		// Add Listener
		try {
			Method m = person.getClass().getMethod("addPropertyChangeListener",
					String.class, PropertyChangeListener.class);
			m.invoke(person, "name", listener);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Change name property.
		person.setName("New name");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

}
