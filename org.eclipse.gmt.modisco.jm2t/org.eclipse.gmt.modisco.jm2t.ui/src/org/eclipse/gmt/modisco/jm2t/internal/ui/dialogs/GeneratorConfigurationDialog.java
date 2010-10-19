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
package org.eclipse.gmt.modisco.jm2t.internal.ui.dialogs;

import java.util.List;

import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.DialogField;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.IDialogFieldListener;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.StringDialogField;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Dilaog used to create/edit a generator configuration.
 * 
 */
public class GeneratorConfigurationDialog extends StatusDialog implements
		IDialogFieldListener {

	private IGeneratorType generatorType;

	private StringDialogField fNameDialogField;

	public GeneratorConfigurationDialog(Shell parent,
			IGeneratorType generatorType,
			List<IGeneratorConfiguration> configurations) {
		this(parent, (IGeneratorConfiguration) null, configurations);
		this.generatorType = generatorType;
	}

	public GeneratorConfigurationDialog(Shell parent,
			IGeneratorConfiguration generatorConfiguration,
			List<IGeneratorConfiguration> configurations) {
		super(parent);
		if (generatorConfiguration != null) {
			generatorType = generatorConfiguration.getGeneratorType();
		}
		createControls(generatorConfiguration);
	}

	private void createControls(IGeneratorConfiguration generatorConfiguration) {
		fNameDialogField = new StringDialogField();
		fNameDialogField
				.setLabelText(Messages.GeneratorConfigurationDialog_name);
		fNameDialogField.setDialogFieldListener(this);

		init(generatorConfiguration);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		Composite inner = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.numColumns = 2;
		inner.setLayout(layout);

		fNameDialogField.doFillIntoGrid(inner, 2);
		// fPriorityDialogField.doFillIntoGrid(inner, 2);

		// LayoutUtil.setHorizontalGrabbing(fNameDialogField.getTextControl(null));
		// LayoutUtil.setWidthHint(fNameDialogField.getTextControl(null),
		// convertWidthInCharsToPixels(45));
		//
		fNameDialogField.postSetFocusOnDialogField(parent.getDisplay());

		applyDialogFont(composite);

		// PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
		// IJavaHelpContextIds.TASK_TAG_INPUT_DIALOG);

		return composite;
	}

	private void init(IGeneratorConfiguration generatorConfiguration) {
		if (generatorConfiguration == null) {
			return;
		}
		fNameDialogField.setText(generatorConfiguration.getName());
	}

	public IGeneratorConfiguration getResult() {
		IGeneratorConfiguration generatorConfiguration = generatorType
				.createGeneratorConfiguration();
		generatorConfiguration.setName(fNameDialogField.getText());
		return generatorConfiguration;
	}

	public void dialogFieldChanged(DialogField field) {
		doValidation();
	}

	private void doValidation() {
		StatusInfo status = new StatusInfo();
		String newText = fNameDialogField.getText();
		if (newText.length() == 0) {
			status.setError(Messages.GeneratorConfigurationDialog_error_enterName);
		} else {
			if (newText.indexOf(',') != -1) {
				// status.setError(PreferencesMessages.TodoTaskInputDialog_error_comma);
				// } else if (fExistingNames.contains(newText)) {
				// status.setError(PreferencesMessages.TodoTaskInputDialog_error_entryExists);
				// } else if (Character.isWhitespace(newText.charAt(0)) ||
				// Character.isWhitespace(newText.charAt(newText.length() - 1)))
				// {
				// status.setError(PreferencesMessages.TodoTaskInputDialog_error_noSpace);
			}
		}
		updateStatus(status);
	}

}
