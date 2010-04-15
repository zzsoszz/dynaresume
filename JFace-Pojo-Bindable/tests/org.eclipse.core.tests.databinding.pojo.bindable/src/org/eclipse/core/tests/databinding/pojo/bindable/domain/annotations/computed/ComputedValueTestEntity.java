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
package org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.computed;

import java.beans.PropertyChangeEvent;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;

/**
 * Model sample with computed values. Computed values are managed with
 * {@link Bindable#dependsOn()}.
 * 
 * <p>
 * {@link ComputedValueTestEntity#getRatio()} depends on
 * {@link ComputedValueTestEntity#setSellingPrice(double)} and
 * {@link ComputedValueTestEntity#setBuyingPrice(double)}.
 * 
 * It means that when setSellingPrice/setBuyingPrice are called, it fire 2
 * events {@link PropertyChangeEvent} :
 * 
 * <ul>
 * <li>one for sellingPrice/buyingPrice change</li>
 * <li>one for ratio change</li>
 * </ul>
 * 
 * </p>
 */
public class ComputedValueTestEntity {

	private double sellingPrice;
	private double buyingPrice;

	public ComputedValueTestEntity(double sellingPrice, double buyingPrice) {
		this.sellingPrice = sellingPrice;
		this.buyingPrice = buyingPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	@Bindable(dependsOn = { "sellingPrice", "buyingPrice" })
	public double getRatio() {
		return sellingPrice / buyingPrice;
	}

	@Bindable(dependsOn = ("buyingPrice"))
	public boolean isCheapBuyingPrice() {
		return buyingPrice < 100;
	}

}
