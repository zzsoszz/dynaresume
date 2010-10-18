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
package org.eclipse.gmt.modisco.jm2t.ui.wizard;

import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Composite to create generator configuration.
 * 
 */
public class GeneratorConfigurationComposite extends Composite {

	private IWizardHandle wizardHandle;
	private IGeneratorConfiguration generatorConfiguration;
	private Text name;

	public GeneratorConfigurationComposite(Composite parent,
			IWizardHandle wizardHandle) {
		super(parent, SWT.NONE);
		this.wizardHandle = wizardHandle;
		initializeWizard(parent, wizardHandle);
		createControl();
	}

	protected void initializeWizard(Composite parent,
			IWizardHandle wizardHandle2) {

	}

	public IWizardHandle getWizardHandle() {
		return wizardHandle;
	}

	/**
	 * Provide a wizard page to create a generator configuration.
	 */
	protected void createControl() {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		setLayout(layout);
		setLayoutData(new GridData(GridData.FILL_BOTH));
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
		// ContextIds.RUNTIME_COMPOSITE);

		Label label = new Label(this, SWT.NONE);
		label.setText(Messages.generatorConfigurationName);
		GridData data = new GridData();
		label.setLayoutData(data);

		name = new Text(this, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(data);

		init();
		validate();

		Dialog.applyDialogFont(this);

		name.forceFocus();
	}

	protected void init() {
		if (name == null || generatorConfiguration == null)
			return;

		name.setText(generatorConfiguration.getName());

	}

	protected void validate() {
		if (generatorConfiguration == null) {
			wizardHandle.setMessage("", IMessageProvider.ERROR);
			return;
		}

	}

	public void setGeneratorConfiguration(
			IGeneratorConfiguration generatorConfiguration) {
		this.generatorConfiguration = generatorConfiguration;
	}

}
