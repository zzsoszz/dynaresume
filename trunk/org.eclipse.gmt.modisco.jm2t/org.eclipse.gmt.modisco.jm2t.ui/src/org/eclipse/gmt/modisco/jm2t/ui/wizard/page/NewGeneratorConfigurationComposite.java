/*******************************************************************************
 * Copyright (c) 2010 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.ui.wizard.page;

import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.SWTUtil;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite to create a new generator configuration.
 * 
 */
public class NewGeneratorConfigurationComposite extends Composite {

	private IWizardHandle wizard;
	private TaskModel taskModel;
	private IGeneratorType generatorType;
	private IModelConverterType modelConverterType;

	public NewGeneratorConfigurationComposite(Composite parent,
			IWizardHandle wizard, TaskModel tm) {
		super(parent, SWT.NONE);

		this.wizard = wizard;
		this.taskModel = tm;

		createControl();

		wizard.setTitle(Messages.wizNewGeneratorConfigurationTitle);
		wizard.setDescription(Messages.wizNewGeneratorConfigurationDescription);
		// wizard.setImageDescriptor(ImageResource.getImageDescriptor(ImageResource.IMG_WIZBAN_NEW_RUNTIME));
	}

	private void createControl() {
		// initializeDialogUnits(parent);
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = SWTUtil.convertHorizontalDLUsToPixels(this,
				4);
		layout.verticalSpacing = SWTUtil.convertVerticalDLUsToPixels(this, 4);
		setLayout(layout);
	}

	public void setGeneratorType(IGeneratorType generatorType) {
		this.generatorType = generatorType;
	}

	public void setModelConverterType(IModelConverterType modelConverterType) {
		this.modelConverterType = modelConverterType;
	}

}
