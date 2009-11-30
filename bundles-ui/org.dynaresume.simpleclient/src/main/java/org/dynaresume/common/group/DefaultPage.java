package org.dynaresume.common.group;

import org.dynaresume.common.domain.Group;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class DefaultPage extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public DefaultPage() {
		super("New Group");
		setTitle("Wizard Page title");
		setDescription("Create a new Group in your system (might be done only once)");
	}
	private Group newGroup;
	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		
		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		GroupComposite groupComposite = new GroupComposite(container, SWT.NONE);
		// PLQ : start by hand
		GroupCompositeController controller = new GroupCompositeController(
				groupComposite, newGroup);
		WizardPageSupport.create(this, controller.getBindingContext());
		// PLQ : end by hand
	}
	public void setGroup(Group newGroup){
		this.newGroup=newGroup;
	}

}
