package org.dynaresume.common.agency;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.swt.SWT;


public class AgencyCompositeController {
	private AgencyComposite m_agencyComposite;
	private DataBindingContext m_bindingContext;
	private org.dynaresume.core.domain.Agency agency = new org.dynaresume.core.domain.Agency();

	public AgencyCompositeController(AgencyComposite agencyComposite, org.dynaresume.core.domain.Agency newAgency) {
		m_agencyComposite = agencyComposite;
		setAgency(newAgency);
	}

	public AgencyCompositeController(AgencyComposite agencyComposite) {
		m_agencyComposite = agencyComposite;
		if (agency != null) {
			m_bindingContext = initDataBindings();
		}
	}

	private DataBindingContext initDataBindings() {
		IObservableValue codeObserveWidget = SWTObservables.observeText(m_agencyComposite.getCodeText(), SWT.Modify);
		IObservableValue codeObserveValue = BeansObservables.observeValue(agency, "code");
		IObservableValue emailObserveWidget = SWTObservables.observeText(m_agencyComposite.getEmailText(), SWT.Modify);
		IObservableValue emailObserveValue = BeansObservables.observeValue(agency, "email");
		IObservableValue nameObserveWidget = SWTObservables.observeText(m_agencyComposite.getNameText(), SWT.Modify);
		IObservableValue nameObserveValue = BeansObservables.observeValue(agency, "name");
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(codeObserveWidget, codeObserveValue, null, null);
		bindingContext.bindValue(emailObserveWidget, emailObserveValue, null, null);
		bindingContext.bindValue(nameObserveWidget, nameObserveValue, null, null);
		//
		return bindingContext;
	}

	public org.dynaresume.core.domain.Agency getAgency() {
		return agency;
	}

	public void setAgency(org.dynaresume.core.domain.Agency newAgency) {
		setAgency(newAgency, true);
	}
	
	public DataBindingContext getBindingContext() {
		return m_bindingContext;
	}

	
	public void setAgency(org.dynaresume.core.domain.Agency newAgency, boolean update) {
		agency = newAgency;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (agency != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}
}