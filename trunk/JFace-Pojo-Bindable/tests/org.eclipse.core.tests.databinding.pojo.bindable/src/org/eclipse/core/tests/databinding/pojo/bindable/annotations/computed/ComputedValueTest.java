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

import junit.framework.TestCase;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.computed.ComputedValueTestEntity;

/**
 * Call this test with JVM Parameters
 * 
 * -javaagent:lib/org.eclipse.core.databinding.pojo.bindable_1.0.0.jar
 * -Dbindable.packages=org.eclipse.core.tests.databinding.pojo.bindable.domain.
 * annotations.computed -Dbindable.use_annotation=true -Dbindable.debug=true
 * 
 * See the launch/ComputedValueTest.launch
 * 
 * Test with computed values by using model {@link ComputedValueTestEntity} wich
 * use {@link Bindable#dependsOn()}.
 * 
 * @Bindable(dependsOn = { "sellingPrice", "buyingPrice" }) public double
 *                     getRatio() { return sellingPrice / buyingPrice; }
 * @Bindable(dependsOn = ("buyingPrice")) public boolean isCheapBuyingPrice() {
 *                     return buyingPrice < 100; }
 * 
 * 
 * 
 */
public class ComputedValueTest extends TestCase {

	private PropertyChangeEvent testEvent = null;

	class RatioListener implements PropertyChangeListener {
		private boolean sellingPriceCalled = false;
		private boolean buyingPriceCalled = false;
		private boolean ratioCalled = false;

		private double sellingOld;
		private double buyingOld;
		private double ratioOld;

		public void propertyChange(PropertyChangeEvent evt) {
			System.out.printf("Found change in %s, old = %s, new = %s\n", evt
					.getPropertyName(), evt.getOldValue(), evt.getNewValue());
			if ("buyingPrice".equals(evt.getPropertyName())) {
				buyingPriceCalled = true;
				buyingOld = (Double) evt.getOldValue();
			}
			if ("sellingPrice".equals(evt.getPropertyName())) {
				sellingPriceCalled = true;
				sellingOld = (Double) evt.getOldValue();
			}
			if ("ratio".equals(evt.getPropertyName())) {
				ratioCalled = true;
				ratioOld = (Double) evt.getOldValue();
			}
		}

		public void clear() {
			ratioCalled = false;
			sellingPriceCalled = false;
			buyingPriceCalled = false;
		}

		public boolean isRatioCalled() {
			return ratioCalled;
		}

		public boolean isSellingPriceCalled() {
			return sellingPriceCalled;
		}

		public boolean isBuyingPriceCalled() {
			return buyingPriceCalled;
		}

		public double getSellingOld() {
			return sellingOld;
		}

		public double getBuyingOld() {
			return buyingOld;
		}

		public double getRatioOld() {
			return ratioOld;
		}
	};

	/**
	 * Test {@link ComputedValueTestEntity#getRatio()} computed value.
	 * 
	 * @throws Exception
	 */
	public void testComputedValue() throws Exception {
		ComputedValueTestEntity p = new ComputedValueTestEntity(4, 2);
		assertFieldsEqual(p, 4., 2., 2.);
		assertTrue(p instanceof BindableAware);

		RatioListener l = new RatioListener();
		((BindableAware) p).addPropertyChangeListener(l);

		assertCalled(l, false, false, false);
		p.setBuyingPrice(6);
		// selling price old value of 0. because there was no property change
		// event for it
		assertOldPropertyChangedValues(l, 0., 2., 2.);
		assertFieldsEqual(p, 4., 6., 4. / 6.);
		assertCalled(l, false, true, true);

		p.setSellingPrice(20);
		assertOldPropertyChangedValues(l, 4., 2., 4. / 6.);
		assertFieldsEqual(p, 20., 6., 20. / 6.);
		assertCalled(l, true, false, true);
	}

	/**
	 * Test {@link ComputedValueTestEntity#isCheapBuyingPrice()} computed value.
	 * 
	 */
	public void testCheapBuyingPrice() {

		ComputedValueTestEntity p = new ComputedValueTestEntity(4, 2);
		assertFieldsEqual(p, 4., 2., 2.);
		assertTrue(p instanceof BindableAware);

		PropertyChangeListener listener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				System.out
						.println("---------- Property ComputedValueTestEntity changed --------");
				System.out.println("  PropertyName=" + event.getPropertyName());
				System.out.println("  OldValue=" + event.getOldValue());
				System.out.println("  NewValue=" + event.getNewValue());
				System.out
						.println("------------------------------------------");
				testEvent = event;
			}
		};

		((BindableAware) p).addPropertyChangeListener("cheapBuyingPrice",
				listener);
		testEvent = null;

		p.setBuyingPrice(50);
		assertNull(testEvent);
		testEvent = null;

		p.setBuyingPrice(200);
		assertNotNull(testEvent);
		assertEquals(true, testEvent.getOldValue());
		assertEquals(false, testEvent.getNewValue());
		testEvent = null;

		p.setBuyingPrice(50);
		assertNotNull(testEvent);
		assertEquals(false, testEvent.getOldValue());
		assertEquals(true, testEvent.getNewValue());
		testEvent = null;
	}

	/**
	 * Checks the old values as reported by their previous property change
	 * events; this is 0. if there was no such event.
	 */
	private void assertOldPropertyChangedValues(RatioListener l,
			double selling, double buying, double ratio) {
		assertEquals(selling, l.getSellingOld(), 0.001);
		assertEquals(buying, l.getBuyingOld(), 0.001);
		assertEquals(ratio, l.getRatioOld(), 0.001);
	}

	private void assertCalled(RatioListener l, boolean sellingCalled,
			boolean buyingCalled, boolean ratioCalled) {
		assertEquals(l.isSellingPriceCalled(), sellingCalled);
		assertEquals(l.isBuyingPriceCalled(), buyingCalled);
		assertEquals(l.isRatioCalled(), ratioCalled);
		l.clear();
	}

	private void assertFieldsEqual(ComputedValueTestEntity p,
			double sellingPrice, double buyingPrice, double ratio) {
		assertEquals(sellingPrice, p.getSellingPrice(), 0.001);
		assertEquals(buyingPrice, p.getBuyingPrice(), 0.001);
		assertEquals(ratio, p.getRatio(), 0.001);
	}

}
