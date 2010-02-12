package org.dynaresume.common.agency;

import org.dynaresume.core.domain.Agency;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class AgencyPage extends WizardPage {

	
	/**
	 * Create the wizard.
	 */
	public AgencyPage() {
		super("New Agency");
		setTitle("Create a new group");
		setDescription("Create a new Agency for the group");
	}
	private Agency newGroup;
	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		
		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		AgencyComposite groupComposite = new AgencyComposite(container, SWT.NONE);
		// PLQ : start by hand
		AgencyCompositeController controller = new AgencyCompositeController(
				groupComposite, newGroup);
		WizardPageSupport.create(this, controller.getBindingContext());
		// PLQ : end by hand
	}
	public void setGroup(Agency newGroup){
		this.newGroup=newGroup;
	}
}
