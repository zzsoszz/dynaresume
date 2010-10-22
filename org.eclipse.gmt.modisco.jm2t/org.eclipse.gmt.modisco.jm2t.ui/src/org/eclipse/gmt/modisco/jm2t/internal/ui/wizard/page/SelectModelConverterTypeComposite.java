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
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.SWTUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.ModelConverterTypeComposite;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite which call {@link ModelConverterTypeComposite} to display list of
 * Model converter type (filtered by generator type) to select.
 * 
 */
public class SelectModelConverterTypeComposite extends Composite {

	protected TaskModel taskModel;
	protected IWizardHandle wizard;
	protected ModelConverterTypeComposite comp;

	public SelectModelConverterTypeComposite(Composite parent,
			IWizardHandle wizard, TaskModel tm) {
		super(parent, SWT.NONE);

		this.wizard = wizard;
		this.taskModel = tm;

		createControl();

		wizard.setTitle(Messages.wizSelectModelConverterTypeTitle);
		wizard.setDescription(Messages.wizSelectModelConverterTypeDescription);
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
		comp = new ModelConverterTypeComposite(
				this,
				new ModelConverterTypeComposite.ModelConverterTypeSelectionListener() {
					public void modelConverterTypeSelected(
							IModelConverterType modelConverterType) {
						handleSelection(modelConverterType);
					}
				});
		GridData data = new GridData(GridData.FILL_BOTH);
		data.heightHint = 300;
		comp.setLayoutData(data);

	}

	protected void handleSelection(IModelConverterType modelConverterType) {
		// Tree node is selected, model converter type is null or not
		// put it into the Map TaskModel
		taskModel.putObject(TaskModel.TASK_MODEL_CONVERTER_TYPE,
				modelConverterType);
		// refresh wizard to manage buttons and next page.
		wizard.update();
	}

	/**
	 * Set the generator type used to filter the tree of the model converter
	 * type.
	 * 
	 * @param generatorType
	 */
	public void setGeneratorType(IGeneratorType generatorType) {
		if (comp != null) {
			comp.setGeneratorType(generatorType);
		}
	}

}
