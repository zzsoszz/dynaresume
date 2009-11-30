package org.dynaresume.common.group;

import org.dynaresume.common.domain.Group;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class WizardGroup extends Wizard implements INewWizard{
	private final Group  newGroup ;
	public WizardGroup() {
		setWindowTitle("New Group");
		newGroup = new Group();
	}

	
	public Group getNewGroup() {
		return newGroup;
	}


	@Override
	public void addPages() {
		DefaultPage page1 = new DefaultPage();
		addPage(page1);
		page1.setGroup(newGroup);
	}

	@Override
	public boolean performFinish() {
		System.out.println(newGroup);
		//TODO : call a service that persist the newly created "group"
		return false;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}
	

}
