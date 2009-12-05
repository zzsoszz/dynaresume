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

import org.dynaresume.common.domain.Group;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class GroupPage extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public GroupPage() {
		super("New Group");
		setTitle("Create a new group");
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
