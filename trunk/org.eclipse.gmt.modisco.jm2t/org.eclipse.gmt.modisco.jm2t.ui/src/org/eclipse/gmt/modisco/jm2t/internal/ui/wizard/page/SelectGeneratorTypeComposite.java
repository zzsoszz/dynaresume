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
package org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.page;

import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.SWTUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorTypeComposite;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite which call {@link GeneratorTypeComposite} to display list of
 * Generator type to select.
 * 
 */
public class SelectGeneratorTypeComposite extends Composite {

	protected TaskModel taskModel;
	protected IWizardHandle wizard;

	public SelectGeneratorTypeComposite(Composite parent, IWizardHandle wizard,
			TaskModel tm) {
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
		// // IWorkbenchHelpSystem whs =
		// PlatformUI.getWorkbench().getHelpSystem();
		// whs.setHelp(this, ContextIds.NEW_RUNTIME_WIZARD);

		final GeneratorTypeComposite comp = new GeneratorTypeComposite(this,
				new GeneratorTypeComposite.GeneratorTypeSelectionListener() {
					public void generatorTypeSelected(
							IGeneratorType generatorType) {
						handleSelection(generatorType);
					}
				});
		GridData data = new GridData(GridData.FILL_BOTH);
		data.heightHint = 300;
		comp.setLayoutData(data);

	}

	protected void handleSelection(IGeneratorType generatorType) {
		// Tree node is selected, generator type is null or not
		// put it into the Map TaskModel
		taskModel.putObject(TaskModel.TASK_GENERATOR_TYPE, generatorType);
		// refresh wizard to manage buttons and next page.
		wizard.update();
	}

}
