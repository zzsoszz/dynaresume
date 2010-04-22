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
package org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.fireEvents;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;

/**
 * Model sample where when person is added by calling
 * {@link People#addPerson(Person)}, it fire events "people" with old/new list
 * of Person.
 * 
 * To do that, the model use {@link Bindable#fireEvents()}.
 */
public class People {

	private Collection<Person> people = null;

	@Bindable(fireEvents = { "people" })
	public void addPerson(Person person) {
		if (people == null) {
			people = new ArrayList<Person>();
		}
		people.add(person);
	}

	public Collection<Person> getPeople() {
		return people;
	}

	public static class Person {

	}
}
