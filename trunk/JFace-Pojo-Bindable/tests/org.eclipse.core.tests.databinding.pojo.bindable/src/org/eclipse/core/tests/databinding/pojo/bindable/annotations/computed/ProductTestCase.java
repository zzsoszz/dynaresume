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
package org.eclipse.core.tests.databinding.pojo.bindable.annotations.computed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import junit.framework.TestCase;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.BindableHelper;
import org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.SpringInstrumentationProvider;
import org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.computed.Product;

/**
 * 
 * see launch/ProductTestCase.launch
 */
public class ProductTestCase extends TestCase {

	static {
		// Get Instrumentation instance from spring-agent and add it Bindable
		// ClassFileTransformer for Domain POJO.
		boolean useAnnotation = true;
		DefaultBindableStrategy bindableStrategy = new DefaultBindableStrategy(
				new String[] { "org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.computed" });
		bindableStrategy.setUseAnnotation(useAnnotation);
		bindableStrategy.setDebugMode(true);
		BindableHelper.initialize(bindableStrategy,
				SpringInstrumentationProvider.INSTANCE);

	}

	private PropertyChangeEvent testEvent = null;
	private PropertyChangeEvent testEvent2 = null;
	private PropertyChangeEvent testEvent3 = null;

	/**
	 * Test with Bindable(value=true) into Class.
	 */
	public void testNameBindableAware() {

		Product product = new Product();

		// Instrumentation add BindableAware interface
		assertTrue(product instanceof BindableAware);
		BindableAware computedPriceBindable = (BindableAware) product;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out
						.println("---------- Property ComputedPrice changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
				testEvent = event;
			}
		};

		PropertyChangeListener listener2 = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out
						.println("---------- Property ComputedPrice changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
				testEvent2 = event;
			}
		};

		PropertyChangeListener listener3 = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out
						.println("---------- Property ComputedPrice changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
				testEvent3 = event;
			}
		};

		// addPropertyChangeListener and change user name
		computedPriceBindable.addPropertyChangeListener("currentLocale",
				listener);
		computedPriceBindable.addPropertyChangeListener("name", listener2);
		computedPriceBindable.addPropertyChangeListener("description",
				listener3);

		product.setCurrentLocale(Locale.FRENCH);

		assertNotNull(testEvent);
		assertEquals(null, testEvent.getOldValue());
		assertEquals(Locale.FRENCH, testEvent.getNewValue());
		assertNotNull(testEvent2);
		assertNotNull(testEvent3);

		testEvent = null;
		testEvent2 = null;
		testEvent3 = null;

		product.setCurrentLocale(Locale.ENGLISH);

		assertNotNull(testEvent);
		assertEquals(Locale.FRENCH, testEvent.getOldValue());
		assertEquals(Locale.ENGLISH, testEvent.getNewValue());
		assertNotNull(testEvent2);
		assertNotNull(testEvent3);

	}
}
