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
import org.eclipse.core.databinding.pojo.bindable.BindableHelper;
import org.eclipse.core.databinding.pojo.bindable.DefaultBindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.SpringInstrumentationProvider;
import org.eclipse.core.tests.databinding.pojo.bindable.domain.AllJavaTypes;

/**
 * Use Bindable Java Agent. Call this test with JVM Parameters
 * 
 * -javaagent:lib/org.eclipse.core.databinding.pojo.bindable_1.0.0.jar
 * -Dbindable .packages=org.eclipse.core.tests.databinding.pojo.bindable.domain
 * 
 * See the launch/BindableWithBindableAgentTestCase.launch
 * 
 */
public class BindableAllJavaTypesWithSpringAgentTestCase extends TestCase {

	// Initialize Bindable.
	static {
		// Get Instrumentation instance from spring-agent and add it Bindable
		// ClassFileTransformer for Domain POJO.
		String genBaseDir = null; //"D:\\tmp";
		DefaultBindableStrategy bindableStrategy = new DefaultBindableStrategy(
				new String[] { "org.eclipse.core.tests.databinding.pojo.bindable.domain" });
		bindableStrategy.setDebugMode(true);
		BindableHelper.initialize(bindableStrategy,
				SpringInstrumentationProvider.INSTANCE);

	}
	private PropertyChangeEvent testEvent = null;;

	// Test with Java Primitives types.

	/**
	 * Test with boolean primitive type property.
	 * 
	 */
	public void testbooleanBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("booleanValue", listener);

		// Change booleanValue
		person.setBooleanValue(true);
		assertNotNull(testEvent);
		assertEquals(false, testEvent.getOldValue());
		assertEquals(true, testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with byte primitive type property.
	 * 
	 */
	public void testbyteBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("byteValue", listener);

		// Change byteValue
		person.setByteValue((byte) 10);
		assertNotNull(testEvent);
		assertEquals((byte) 0, testEvent.getOldValue());
		assertEquals((byte) 10, testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with char primitive type property.
	 * 
	 */
	public void testcharBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("charValue", listener);

		// Change charValue
		person.setCharValue((char) 'z');
		assertNotNull(testEvent);
		assertEquals((char) 'a', testEvent.getOldValue());
		assertEquals((char) 'z', testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with short primitive type property.
	 * 
	 */
	public void testshortBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("shortValue", listener);

		// Change shortValue
		person.setShortValue((short) 10);
		assertNotNull(testEvent);
		assertEquals((short) 0, testEvent.getOldValue());
		assertEquals((short) 10, testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with int primitive type property.
	 * 
	 */
	public void testintBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("intValue", listener);

		// Change intValue
		person.setIntValue(10);
		assertNotNull(testEvent);
		assertEquals((int) 0, testEvent.getOldValue());
		assertEquals((int) 10, testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with long primitive type property.
	 * 
	 */
	public void testlongBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("longValue", listener);

		// Change longValue
		person.setLongValue(10);
		assertNotNull(testEvent);
		assertEquals((long) 0, testEvent.getOldValue());
		assertEquals((long) 10, testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with float primitive type property.
	 * 
	 */
	public void testfloatBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("floatValue", listener);

		// Change floatValue
		person.setFloatValue((float) 10.01);
		assertNotNull(testEvent);
		assertEquals((float) 0, testEvent.getOldValue());
		assertEquals((float) 10.01, testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with double primitive type property.
	 * 
	 */
	public void testdoubleBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("doubleValue", listener);

		// Change doubleValue
		person.setDoubleValue((double) 10.01);
		assertNotNull(testEvent);
		assertEquals((double) 0, testEvent.getOldValue());
		assertEquals((double) 10.01, testEvent.getNewValue());
		testEvent = null;

	}

	public void testStringBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("stringValue", listener);

		// Change stringValue
		person.setStringValue("String 1");
		assertNotNull(testEvent);
		assertEquals(null, testEvent.getOldValue());
		assertEquals("String 1", testEvent.getNewValue());
		testEvent = null;

	}

	/**
	 * Test with java.lang.Short object property.
	 * 
	 */
	public void testShortObjectBindableAware() {

		AllJavaTypes person = new AllJavaTypes();

		// Instrumentation add BindableAware interface
		assertTrue(person instanceof BindableAware);
		BindableAware personBindable = (BindableAware) person;

		// Instrumentation was done with successfull, add Listener
		PropertyChangeListener listener = createPropertyChangeListener();

		// addPropertyChangeListener
		personBindable.addPropertyChangeListener("shortObjectValue", listener);

		// Change shortValue
		person.setShortObjectValue((short) 10);
		assertNotNull(testEvent);
		assertEquals(null, testEvent.getOldValue());
		assertEquals((short) 10, testEvent.getNewValue());
		testEvent = null;

	}

	private PropertyChangeListener createPropertyChangeListener() {
		return new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out
						.println("---------- Property AllJavaTypes changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
				testEvent = event;
			}
		};
	}
}
