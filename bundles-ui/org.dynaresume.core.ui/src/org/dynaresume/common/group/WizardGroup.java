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
package org.dynaresume.common.group;

import org.dynaresume.core.View;
import org.dynaresume.core.domain.Group;
import org.dynaresume.core.service.CoreService;
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
	private CoreService coreService;
	private Group  newGroup ;
	public WizardGroup() {
		setWindowTitle("New Group");
		newGroup = new Group();
		//newGroup.setAddress(new Address());
		
	}

	
	public Group getNewGroup() {
		return newGroup;
	}


	@Override
	public void addPages() {
		GroupPage page1 = new GroupPage();
		addPage(page1);
		page1.setGroup(newGroup);
//		AddressPage page2 = new AddressPage();
//		addPage(page2);
//		page2.setAddress(newGroup.getAddress());
//		page2.setCountries(coreService.getAllCountries());
	}

	@Override
	public boolean performFinish() {
		Group aGroup=coreService.saveGroup(newGroup);
		System.out.println(aGroup.getId());
		
		View viewGroup=(View)workbench.getActiveWorkbenchWindow().getActivePage().findView(View.ID);
		viewGroup.refresh();
		return true;
	}
	private IWorkbench workbench;
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench=workbench;
		
	}
	

}
