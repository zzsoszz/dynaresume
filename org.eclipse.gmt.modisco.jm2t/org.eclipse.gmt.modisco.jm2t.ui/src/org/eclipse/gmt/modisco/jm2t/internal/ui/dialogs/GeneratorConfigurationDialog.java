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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.DialogField;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.IDialogFieldListener;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.IStringButtonAdapter;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.StringButtonDialogField;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.StringDialogField;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
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
		IDialogFieldListener, IStringButtonAdapter {

	private IGeneratorType generatorType;
	private IGeneratorConfiguration generatorConfiguration;

	private StringDialogField configurationNameField;
	private StringButtonDialogField templateFilePathField;
	private StringButtonDialogField targetContainerPathField;

	private IJM2TProject project;

	public GeneratorConfigurationDialog(Shell parent, IJM2TProject project,
			IGeneratorType generatorType,
			List<IGeneratorConfiguration> configurations) {
		this(parent, project, (IGeneratorConfiguration) null, configurations);
		this.generatorType = generatorType;
	}

	public GeneratorConfigurationDialog(Shell parent, IJM2TProject project,
			IGeneratorConfiguration generatorConfiguration,
			List<IGeneratorConfiguration> configurations) {
		super(parent);
		this.generatorConfiguration = generatorConfiguration;
		if (generatorConfiguration != null) {
			generatorType = generatorConfiguration.getGeneratorType();
		}
		this.project = project;
		createControls();
	}

	private void createControls() {
		configurationNameField = new StringDialogField();
		configurationNameField
				.setLabelText(Messages.GeneratorConfigurationDialog_name);
		configurationNameField.setDialogFieldListener(this);

		templateFilePathField = new StringButtonDialogField(this);
		templateFilePathField.setDialogFieldListener(this);
		templateFilePathField
				.setLabelText(Messages.GeneratorConfigurationDialog_templateFile);
		templateFilePathField.setButtonLabel(Messages.browseButton);

		targetContainerPathField = new StringButtonDialogField(this);
		targetContainerPathField.setDialogFieldListener(this);
		targetContainerPathField
				.setLabelText(Messages.GeneratorConfigurationDialog_targetContainer);
		targetContainerPathField.setButtonLabel(Messages.browseButton);

		// Set the old settings
		setDefaults();
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		Composite inner = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.numColumns = 3;
		inner.setLayout(layout);

		configurationNameField.doFillIntoGrid(inner, 3);
		templateFilePathField.doFillIntoGrid(inner, 3);
		targetContainerPathField.doFillIntoGrid(inner, 3);

		// LayoutUtil.setHorizontalGrabbing(fNameDialogField.getTextControl(null));
		// LayoutUtil.setWidthHint(fNameDialogField.getTextControl(null),
		// convertWidthInCharsToPixels(45));
		//
		configurationNameField.postSetFocusOnDialogField(parent.getDisplay());

		applyDialogFont(composite);

		// PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
		// IJavaHelpContextIds.TASK_TAG_INPUT_DIALOG);

		return composite;
	}

	private void setDefaults() {
		if (generatorConfiguration == null) {
			return;
		}
		configurationNameField.setText(generatorConfiguration.getName());
		IPath templatePath = generatorConfiguration.getTemplatePath();
		if (templatePath != null) {
			templateFilePathField.setText(String.valueOf(templatePath));
		}
		IPath targetContainerPath = generatorConfiguration
				.getTargetContainerPath();
		if (targetContainerPath != null) {
			targetContainerPathField.setText(String
					.valueOf(targetContainerPath));
		}
	}

	public IGeneratorConfiguration getResult() {
		IModelConverterType modelConverterType = null;
		if (modelConverterType == null) {
			IModelConverterType[] supportedModelConverterTypes = generatorType
					.getSupportedModelConverterTypes();
			if (supportedModelConverterTypes.length > 0) {
				modelConverterType = supportedModelConverterTypes[0];
			}
		}
		IGeneratorConfiguration generatorConfiguration = generatorType
				.createGeneratorConfiguration(modelConverterType, project);
		generatorConfiguration.setName(configurationNameField.getText());
		generatorConfiguration.setTemplatePath(new Path(templateFilePathField
				.getText()));
		generatorConfiguration.setTargetContainerPath(new Path(
				targetContainerPathField.getText()));
		return generatorConfiguration;
	}

	public void dialogFieldChanged(DialogField field) {
		doValidation();
	}

	public void changeControlPressed(DialogField field) {
		if (field == templateFilePathField) {
			IPath templateFilePath = selectTemplateFile();
			if (templateFilePath != null) {
				templateFilePathField.setText(templateFilePath.toString());
			}
			return;
		}
		if (field == targetContainerPathField) {
			IPath targetContainerPath = selectTargetContainer();
			if (targetContainerPath != null) {
				targetContainerPathField
						.setText(targetContainerPath.toString());
			}
			return;
		}
	}

	private void doValidation() {
		StatusInfo status = new StatusInfo();
		String newText = configurationNameField.getText();
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

	/**
	 * Opens a dialog to choose a template file.
	 */
	private IPath selectTemplateFile() {
		String initSelection = templateFilePathField.getText();

		ViewerFilter filter = null;// new ArchiveFileFilter((List) null, false);

		IResource initSel = null;
		IContainer fWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		if (initSelection.length() > 0) {
			initSel = fWorkspaceRoot.findMember(new Path(initSelection));
		}
		if (initSel == null) {
			// initSel= fWorkspaceRoot.findMember(fEntry.getPath());
		}

		TemplateFileSelectionDialog dialog = new TemplateFileSelectionDialog(
				getShell(), false, false);
		dialog.setTitle("AA");
		dialog.setMessage("BB");
		dialog.setInput(fWorkspaceRoot);
		dialog.setInitialSelection(initSel);
		if (dialog.open() == Window.OK) {
			IResource res = (IResource) dialog.getFirstResult();
			return res.getFullPath();
		}
		return null;
	}

	/**
	 * Opens a dialog to choose a target container where files must be
	 * generated.
	 */
	private IPath selectTargetContainer() {
		String initSelection = templateFilePathField.getText();

		ViewerFilter filter = null;// new ArchiveFileFilter((List) null, false);

		IResource initSel = null;
		IContainer fWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		if (initSelection.length() > 0) {
			initSel = fWorkspaceRoot.findMember(new Path(initSelection));
		}
		if (initSel == null) {
			// initSel= fWorkspaceRoot.findMember(fEntry.getPath());
		}

		FolderSelectionDialog dialog = new FolderSelectionDialog(getShell());
		dialog.setTitle("AA");
		dialog.setMessage("BB");
		dialog.setInput(fWorkspaceRoot);
		dialog.setInitialSelection(initSel);
		if (dialog.open() == Window.OK) {
			IResource res = (IResource) dialog.getFirstResult();
			return res.getFullPath();
		}
		return null;
	}

}
