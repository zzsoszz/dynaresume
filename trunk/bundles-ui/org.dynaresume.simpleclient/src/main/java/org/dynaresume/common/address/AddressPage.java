package org.dynaresume.common.address;

import java.util.List;

import org.dynaresume.common.domain.Address;
import org.dynaresume.common.domain.Country;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class AddressPage extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public AddressPage() {
		super("wizardPage");
		setTitle("Create a new address");
		setDescription("New Address");
	}
	private Address address;
	private List<Country> countries;
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		AddressComposite addressComposite  = new AddressComposite(container, SWT.NONE);
		AddressCompositeController m_controller = new AddressCompositeController(addressComposite);
		m_controller.initCountryViewer(countries);
		//at last set the model...
		m_controller.setAddress(address);
		
	}
	public void setAddress(Address address){
		this.address=address;
	}
	public void setCountries(List<Country> countries){
		this.countries=countries;
	}
}
