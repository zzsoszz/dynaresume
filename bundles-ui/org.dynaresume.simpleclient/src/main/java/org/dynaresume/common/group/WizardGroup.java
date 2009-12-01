package org.dynaresume.common.group;

import org.dynaresume.common.domain.Address;
import org.dynaresume.common.domain.Group;
import org.dynaresume.common.service.AgenceService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component(value="newGroupWizard")
@Scope("prototype")
public class WizardGroup extends Wizard implements INewWizard{
	
	@Autowired
	private AgenceService agenceService;
	private Group  newGroup ;
	public WizardGroup() {
		setWindowTitle("New Group");
		newGroup = new Group();
		newGroup.setAddress(new Address());
		
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
		Group aGroup=agenceService.createGroup(newGroup);
		System.out.println(aGroup.getId());
		
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}
	

}
