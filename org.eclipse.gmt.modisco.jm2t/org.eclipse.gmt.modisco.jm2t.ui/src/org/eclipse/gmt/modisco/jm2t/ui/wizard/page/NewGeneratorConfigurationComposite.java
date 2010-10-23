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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogs.FolderSelectionDialog;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogs.TemplateFileSelectionDialog;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.SWTUtil;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
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
	private Text templatePath;
	private Text targetPath;

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

		final Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText(Messages.NewGeneratorConfigurationComposite_name);
		GridData data = new GridData();
		nameLabel.setLayoutData(data);

		name = new Text(this, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan =2;
		name.setLayoutData(data);
		name.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				generatorConfiguration.setName(name.getText());
				validate();
			}
		});
		
		final Label templatePathLabel = new Label(this, SWT.NONE);
		templatePathLabel.setText(Messages.NewGeneratorConfigurationComposite_templatePath);
		data = new GridData();
		templatePathLabel.setLayoutData(data);

		templatePath = new Text(this, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		templatePath.setLayoutData(data);
		templatePath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				generatorConfiguration.setTemplatePath(new Path(templatePath.getText()));
				validate();
			}
		});
		Button templateBrowseButton = SWTUtil.createButton(this, Messages.browseButton);
		templateBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				IPath templateFilePath = selectTemplateFile();
				if (templateFilePath != null) {
					templatePath.setText(templateFilePath.toString());
				}
			}
		});
		
		final Label targetPathLabel = new Label(this, SWT.NONE);
		targetPathLabel.setText(Messages.NewGeneratorConfigurationComposite_targetPath);
		data = new GridData();
		targetPathLabel.setLayoutData(data);

		targetPath = new Text(this, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		targetPath.setLayoutData(data);
		targetPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				generatorConfiguration.setTargetContainerPath(new Path(targetPath.getText()));
				validate();
			}
		});
		Button targetBrowseButton = SWTUtil.createButton(this, Messages.browseButton);
		targetBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				IPath targetContainerPath = selectTargetContainer();
				if (targetContainerPath != null) {
					targetPath
							.setText(targetContainerPath.toString());
				}
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
		
		if (generatorConfiguration.getTemplatePath() != null)
			templatePath.setText(generatorConfiguration.getTemplatePath().toString());
		else
			templatePath.setText("");
		
		if (generatorConfiguration.getTargetContainerPath() != null)
			targetPath.setText(generatorConfiguration.getTargetContainerPath().toString());
		else
			targetPath.setText("");
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
		this.generatorConfiguration = generatorConfiguration.copy();
		taskModel.putObject(TaskModel.TASK_GENERATOR_CONFIGURATION,
				this.generatorConfiguration);
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
	
	/**
	 * Opens a dialog to choose a template file.
	 */
	private IPath selectTemplateFile() {
		String initSelection = templatePath.getText();

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
		dialog.setTitle(Messages.TemplateFileSelectionDialog_title);
		dialog.setMessage(Messages.TemplateFileSelectionDialog_message);
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
		String initSelection = targetPath.getText();

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
		dialog.setTitle(Messages.FolderSelectionDialog_title);
		dialog.setMessage(Messages.FolderSelectionDialog_message);
		dialog.setInput(fWorkspaceRoot);
		dialog.setInitialSelection(initSel);
		if (dialog.open() == Window.OK) {
			IResource res = (IResource) dialog.getFirstResult();
			return res.getFullPath();
		}
		return null;
	}


}
