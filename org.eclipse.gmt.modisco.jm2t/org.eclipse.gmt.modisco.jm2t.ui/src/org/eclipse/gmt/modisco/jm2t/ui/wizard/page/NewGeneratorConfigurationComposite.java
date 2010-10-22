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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Composite to create a new generator configuration or edit generator
 * configuration.
 * 
 */
public class NewGeneratorConfigurationComposite extends Composite {

	private IWizardHandle wizard;
	private TaskModel taskModel;
	private Text name;
	private IGeneratorConfiguration generatorConfiguration;

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
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		setLayout(layout);
		setLayoutData(new GridData(GridData.FILL_BOTH));
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
		// ContextIds.RUNTIME_COMPOSITE);

		Label label = new Label(this, SWT.NONE);
		label.setText(Messages.NewGeneratorConfigurationComposite_name);
		GridData data = new GridData();
		label.setLayoutData(data);

		name = new Text(this, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(data);
		name.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				generatorConfiguration.setName(name.getText());
				validate();
			}
		});
		init();
		validate();

		Dialog.applyDialogFont(this);

		name.forceFocus();
	}

	protected void init() {
		if (name == null || generatorConfiguration == null)
			return;

		if (generatorConfiguration.getName() != null)
			name.setText(generatorConfiguration.getName());
		else
			name.setText("");
	}

	/**
	 * Initialize the composite with generator + model converter type. In this
	 * case new generator configuration will be created.
	 * 
	 * @param generatorType
	 * @param modelConverterType
	 */
	public void init(IGeneratorType generatorType,
			IModelConverterType modelConverterType) {
		// Create new generator configuration
		this.generatorConfiguration = generatorType
				.createGeneratorConfiguration(modelConverterType,
						getJM2TProject());
		taskModel.putObject(TaskModel.TASK_GENERATOR_CONFIGURATION,
				generatorConfiguration);

		init();
		validate();
	}

	/**
	 * Initialize the composite with the generator configuration (edit the
	 * configuration).
	 * 
	 * @param generatorConfiguration
	 */
	public void init(IGeneratorConfiguration generatorConfiguration) {
		this.generatorConfiguration = generatorConfiguration;
		init();
		validate();
	}

	protected void validate() {
		if (generatorConfiguration == null) {
			wizard.setMessage("", IMessageProvider.ERROR);
			return;
		}
		IStatus status = generatorConfiguration.validate();
		if (status == null || status.isOK())
			wizard.setMessage(null, IMessageProvider.NONE);
		else if (status.getSeverity() == IStatus.WARNING)
			wizard.setMessage(status.getMessage(), IMessageProvider.WARNING);
		else
			wizard.setMessage(status.getMessage(), IMessageProvider.ERROR);
		wizard.update();
	}

	protected IJM2TProject getJM2TProject() {
		return (IJM2TProject) taskModel.getObject(TaskModel.TASK_JM2T_PROJECT);
	}

}
