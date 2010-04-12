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
package org.eclipse.core.tests.databinding.pojo.bindable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.tests.databinding.pojo.bindable.domain.User;

/**
 * Use Bindable Java Agent. Call this test with JVM Parameters
 * 
 * -javaagent:lib/org.eclipse.core.databinding.pojo.bindable_1.0.0.jar
 * -Dbindable .packages=org.eclipse.core.tests.databinding.pojo.bindable.domain
 * 
 * See the launch/BindableWithBindableAgentTestCase.launch
 * 
 */
public class BindableWithBindableAgentTestCase extends TestCase {

	private PropertyChangeEvent testEvent = null;;

	public void testBindableAware() {

		User user = new User();

		// Instrumentation add BindableAware interface
		assertEquals(true, user instanceof BindableAware);
		BindableAware userBindable = (BindableAware) user;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out.println("---------- Property User changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
				testEvent = event;
			}
		};

		// addPropertyChangeListener and change user name
		userBindable.addPropertyChangeListener("name", listener);
		/*
		 * Same code like reflection : try { Method m =
		 * user.getClass().getMethod("addPropertyChangeListener", String.class,
		 * PropertyChangeListener.class); m.invoke(user, "name", listener); }
		 * catch (Exception e) {
		 * 
		 * }
		 */
		user.setName("Name 1");
		assertNotNull(testEvent);
		assertEquals(null, testEvent.getOldValue());
		assertEquals("Name 1", testEvent.getNewValue());
		testEvent = null;

		// Change user name
		user.setName("Name 2");
		assertNotNull(testEvent);
		assertEquals("Name 1", testEvent.getOldValue());
		assertEquals("Name 2", testEvent.getNewValue());
		testEvent = null;

		// removePropertyChangeListener and change user name
		userBindable.removePropertyChangeListener("name", listener);
		/*
		 * Same code like reflection : try { Method m =
		 * user.getClass().getMethod("removePropertyChangeListener",
		 * String.class, PropertyChangeListener.class); m.invoke(user, "name",
		 * listener); } catch (Exception e) { }
		 */

		user.setName("Name 3");
		assertNull(testEvent);

		// addPropertyChangeListener and change user name
		userBindable.addPropertyChangeListener("name", listener);
		user.setName("Name 3");
		assertNull(testEvent); // No change

		// Change user name
		user.setName("Name 4");
		assertNotNull(testEvent);
		assertEquals("Name 3", testEvent.getOldValue());
		assertEquals("Name 4", testEvent.getNewValue());
		testEvent = null;

	}
}
