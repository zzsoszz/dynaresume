package org.dynaresume.common.address;
import org.dynaresume.common.service.AgenceService;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="AddressCompositeController")
@Scope("prototype")
public class AddressCompositeController {
	@Autowired
	private AgenceService agenceService;

	private AddressComposite m_addressComposite;
	private DataBindingContext m_bindingContext;
	private org.dynaresume.common.domain.Address address = new org.dynaresume.common.domain.Address();

	public AddressCompositeController(AddressComposite addressComposite, org.dynaresume.common.domain.Address newAddress) {
		m_addressComposite = addressComposite;
		setAddress(newAddress);
	}

	public AddressCompositeController(AddressComposite addressComposite) {
		m_addressComposite = addressComposite;
		if (address != null) {
			m_bindingContext = initDataBindings();
		}
	}

	private DataBindingContext initDataBindings() {
		IObservableValue cityObserveWidget = SWTObservables.observeText(m_addressComposite.getCityText(), SWT.Modify);
		IObservableValue cityObserveValue = BeansObservables.observeValue(address, "city");
		IObservableValue faxObserveWidget = SWTObservables.observeText(m_addressComposite.getFaxText(), SWT.Modify);
		IObservableValue faxObserveValue = BeansObservables.observeValue(address, "fax");
		IObservableValue telephoneObserveWidget = SWTObservables.observeText(m_addressComposite.getTelephoneText(), SWT.Modify);
		IObservableValue telephoneObserveValue = BeansObservables.observeValue(address, "telephone");
		IObservableValue zipCodeObserveWidget = SWTObservables.observeText(m_addressComposite.getZipCodeText(), SWT.Modify);
		IObservableValue zipCodeObserveValue = BeansObservables.observeValue(address, "zipCode");
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(cityObserveWidget, cityObserveValue, null, null);
		bindingContext.bindValue(faxObserveWidget, faxObserveValue, null, null);
		bindingContext.bindValue(telephoneObserveWidget, telephoneObserveValue, null, null);
		bindingContext.bindValue(zipCodeObserveWidget, zipCodeObserveValue, null, null);
		//
		return bindingContext;
	}

	public org.dynaresume.common.domain.Address getAddress() {
		return address;
	}

	public void setAddress(org.dynaresume.common.domain.Address newAddress) {
		setAddress(newAddress, true);
	}

	public void setAddress(org.dynaresume.common.domain.Address newAddress, boolean update) {
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
}