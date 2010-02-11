package org.dynaresume.common.agency;

import org.dynaresume.common.address.AddressPage;
import org.dynaresume.core.domain.Address;
import org.dynaresume.core.domain.Agency;
import org.dynaresume.core.service.CoreService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="newAgencyWizard")
@Scope("prototype")
public class WizardAgency extends Wizard implements INewWizard {

	@Autowired
	private CoreService coreService;
	private Agency  newAgency ;
	public WizardAgency() {
		setWindowTitle("New Agency");
		
		newAgency = new Agency();
		newAgency.setAddress(new Address());
	}

	@Override
	public void addPages() {
		AgencyPage page1 = new AgencyPage();
		addPage(page1);
		page1.setGroup(newAgency);
		AddressPage page2 = new AddressPage();
		addPage(page2);
		page2.setAddress(newAgency.getAddress());
		page2.setCountries(coreService.getAllCountries());
	}

	@Override
	public boolean performFinish() {
		return false;
	}
	private IWorkbench workbench;
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench=workbench;
		
	}

}
