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
package org.eclipse.core.examples.databinding.pojo.bindable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.examples.databinding.pojo.bindable.model.PojoPerson;

/**
 * A basic sample with Bindable Pojo which transform a simple Pojo to implements
 * {@link BindableAware}.
 * 
 * This program must be launched with JVM parameters :
 * -javaagent:lib/org.eclipse.core.databinding.pojo.bindable_1.0.0.jar
 * -Dbindable.packages=org.eclipse.core.examples.databinding.pojo.bindable.model
 * -Dbindable.debug=true
 * 
 * See launch launch/PojoPersonBindableTest.launch
 */
public class PojoPersonBindableTest {

	public static void main(String[] args) {

		// Create listener
		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out
						.println("---------- Property PojoPerson changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
			}
		};

		// Create Pojo instance
		PojoPerson person = new PojoPerson();
		// Add listener
		// Add listener
		if (person instanceof BindableAware) {
			((BindableAware) person)
					.addPropertyChangeListener("name", listener);
		}
		// Here Change "name" property of PojoPerson is tracked.
		person.setName("New name");
		// Remove listener
		if (person instanceof BindableAware) {
			((BindableAware) person).removePropertyChangeListener("name",
					listener);
		}
		// Here Change "name" property of PojoPerson is not tracked.
		person.setName("New name2");
	}
}