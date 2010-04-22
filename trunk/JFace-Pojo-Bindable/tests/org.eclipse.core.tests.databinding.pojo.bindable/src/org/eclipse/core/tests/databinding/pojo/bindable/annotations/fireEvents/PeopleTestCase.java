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
package org.eclipse.core.tests.databinding.pojo.bindable.annotations.fireEvents;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

import junit.framework.TestCase;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.BindableHelper;
import org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.SpringInstrumentationProvider;
import org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.fireEvents.People;
import org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.fireEvents.People.Person;

/**
 * Test with {@link Bindable#fireEvents()} by using {@link People} model. When
 * person is added to a people by calling {@link People#addPerson(Person)}, it
 * fire events "people" with old/new list of Person.
 * 
 * see launch/PeopleTestCase.launch
 * 
 */
public class PeopleTestCase extends TestCase {

	static {
		// Get Instrumentation instance from spring-agent and add it Bindable
		// ClassFileTransformer for Domain POJO.
		boolean useAnnotation = true;
		DefaultBindableStrategy bindableStrategy = new DefaultBindableStrategy(
				new String[] { "org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.fireEvents" });
		bindableStrategy.setUseAnnotation(useAnnotation);
		bindableStrategy.setDebugMode(true);
		// bindableStrategy.setGenBaseDir("D://_Projets/Personal/workspace-JFace-Pojo-Bindable/bindable-pojo-test/lib");
		BindableHelper.initialize(bindableStrategy,
				SpringInstrumentationProvider.INSTANCE);

	}

	private PropertyChangeEvent testEvent = null;

	public void testPeopleAdded() {
		People people = new People();

		// Instrumentation add BindableAware interface
		assertTrue(people instanceof BindableAware);
		BindableAware peopleBindable = (BindableAware) people;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out
						.println("---------- Property People changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
				testEvent = event;
			}
		};

		peopleBindable.addPropertyChangeListener("people", listener);

		// Add a pesron
		people.addPerson(new Person());
		assertNotNull(testEvent);
		assertEquals(null, testEvent.getOldValue());
		assertTrue(testEvent.getNewValue() instanceof Collection);
		assertEquals(1, ((Collection<Person>)testEvent.getNewValue()).size());
		testEvent = null;
	}
}
