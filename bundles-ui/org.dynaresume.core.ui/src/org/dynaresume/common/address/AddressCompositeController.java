/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.common.address;
import java.util.Collection;
import java.util.List;

import org.dynaresume.core.domain.Country;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;


public class AddressCompositeController {
	private AddressComposite m_addressComposite;
	private ComboViewer countryViewer;
	private DataBindingContext m_bindingContext;
	private org.dynaresume.core.domain.Address address = new org.dynaresume.core.domain.Address();

	public AddressCompositeController(AddressComposite addressComposite, org.dynaresume.core.domain.Address newAddress) {
		m_addressComposite = addressComposite;
		setAddress(newAddress);
	}

	public AddressCompositeController(AddressComposite addressComposite) {
		m_addressComposite = addressComposite;
		
		
		
	}

	
	public DataBindingContext initDataBindings() {
		IObservableValue cityObserveWidget = SWTObservables.observeText(m_addressComposite.getCityText(), SWT.Modify);
		IObservableValue cityObserveValue = BeansObservables.observeValue(address, "city");
		IObservableValue faxObserveWidget = SWTObservables.observeText(m_addressComposite.getFaxText(), SWT.Modify);
		IObservableValue faxObserveValue = BeansObservables.observeValue(address, "fax");
		IObservableValue telephoneObserveWidget = SWTObservables.observeText(m_addressComposite.getTelephoneText(), SWT.Modify);
		IObservableValue telephoneObserveValue = BeansObservables.observeValue(address, "telephone");
		IObservableValue zipCodeObserveWidget = SWTObservables.observeText(m_addressComposite.getZipCodeText(), SWT.Modify);
		IObservableValue zipCodeObserveValue = BeansObservables.observeValue(address, "zipCode");
		//PLQ hand coded
		IObservableValue countryObserveWidget = ViewersObservables.observeSingleSelection(countryViewer);
		IObservableValue countryObserveValue = BeansObservables.observeValue(address, "country");
		//PLQ
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(cityObserveWidget, cityObserveValue, null, null);
		bindingContext.bindValue(faxObserveWidget, faxObserveValue, null, null);
		bindingContext.bindValue(telephoneObserveWidget, telephoneObserveValue, null, null);
		bindingContext.bindValue(zipCodeObserveWidget, zipCodeObserveValue, null, null);
		bindingContext.bindValue(countryObserveWidget, countryObserveValue, null, null);
		//
		return bindingContext;
	}

	public org.dynaresume.core.domain.Address getAddress() {
		return address;
	}

	public void setAddress(org.dynaresume.core.domain.Address newAddress) {
		setAddress(newAddress, true);
	}

	public void setAddress(org.dynaresume.core.domain.Address newAddress, boolean update) {
		address = newAddress;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (address != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}
	
	public void initCountryViewer(List<Country> countries){
		countryViewer = new ComboViewer(	m_addressComposite.getCountryCombo());
		countryViewer.setContentProvider(new ArrayContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				Collection res=(Collection)inputElement;
				
				return res.toArray();
				
			}
		});
		countryViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
			Country country= (Country)element;
				return country.getLabel();
			}
		});
		countryViewer.setInput(countries);
	}
}