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
package org.eclipse.gmt.modisco.jm2t.internal.ui.preferences;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.internal.ui.JM2TUI;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.EclipseUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.SWTUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorConfigurationComposite;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.TaskWizard;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.WizardTaskUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.fragment.SelectGeneratorTypeWizardFragment;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.fragment.SelectModelConverterTypeWizardFragment;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.fragment.NewGeneratorConfigurationWizardFragment;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * JM2T Property page used to add/edit/remove Generator configurations.
 * 
 */
public class JM2TPropertyPage extends PropertyPage {

	protected Button edit;
	protected Button remove;

	private GeneratorConfigurationComposite generatorConfigurationComp;

	/**
	 * GeneratorConfigurationPreferencesPage constructor comment.
	 */
	public JM2TPropertyPage() {
		super();
		noDefaultAndApplyButton();
	}

	/**
	 * Create the preference options.
	 * 
	 * @param parent
	 *            org.eclipse.swt.widgets.Composite
	 * @return org.eclipse.swt.widgets.Control
	 */
	protected Control createContents(Composite parent) {
		initializeDialogUnits(parent);
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
		// ContextIds.PREF_GENERAL);

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(4);
		layout.verticalSpacing = convertVerticalDLUsToPixels(3);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL);
		composite.setLayoutData(data);

		Label label = new Label(composite, SWT.WRAP);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		label.setLayoutData(data);
		label.setText(Messages.preferenceGeneratorConfigurationsDescription);

		label = new Label(composite, SWT.WRAP);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.verticalIndent = 5;
		label.setLayoutData(data);
		label.setText(Messages.preferenceGeneratorConfigurationsTable);

		generatorConfigurationComp = new GeneratorConfigurationComposite(
				getJM2Project(),
				composite,
				SWT.NONE,
				new GeneratorConfigurationComposite.GeneratorConfigurationSelectionListener() {
					public void generatorConfigurationSelected(
							IGeneratorConfiguration generatorConfiguration) {
						if (generatorConfiguration == null) {
							edit.setEnabled(false);
							remove.setEnabled(false);
						} else {
							edit.setEnabled(true);
							remove.setEnabled(true);
						}
					}
				});
		generatorConfigurationComp.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

		Composite buttonComp = new Composite(composite, SWT.NONE);
		layout = new GridLayout();
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = convertVerticalDLUsToPixels(3);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.numColumns = 1;
		buttonComp.setLayout(layout);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL);
		buttonComp.setLayoutData(data);

		Button add = SWTUtil.createButton(buttonComp, Messages.addButton);
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				doAddButton();
			}
		});

		edit = SWTUtil.createButton(buttonComp, Messages.editButton);
		edit.setEnabled(false);
		edit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				doEditButton();
				// IGeneratorConfiguration generatorConfiguration =
				// generatorConfigurationComp.getSelectedGeneratorConfiguration();
				// if (generatorConfiguration != null) {
				// IGeneratorConfigurationWorkingCopy
				// generatorConfigurationWorkingCopy =
				// generatorConfiguration.createWorkingCopy();
				// if (showWizard(generatorConfigurationWorkingCopy) !=
				// Window.CANCEL) {
				// try {
				// generatorConfigurationComp.refresh(generatorConfiguration);
				// } catch (Exception ex) {
				// // ignore
				// }
				// }
				// }
			}
		});

		remove = SWTUtil.createButton(buttonComp, Messages.removeButton);
		remove.setEnabled(false);
		remove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				doRemoveButton();
				// IGeneratorConfiguration generatorConfiguration =
				// generatorConfigurationComp.getSelectedGeneratorConfiguration();
				// if (removeGeneratorConfiguration(generatorConfiguration))
				// generatorConfigurationComp.remove(generatorConfiguration);
			}
		});

		Dialog.applyDialogFont(composite);

		return composite;
	}

	private void doAddButton() {
		TaskModel taskModel = new TaskModel();
		if (showWizard(taskModel, null) == Window.OK) {
			IGeneratorConfiguration generatorConfiguration = (IGeneratorConfiguration) taskModel
					.getObject(TaskModel.TASK_GENERATOR_CONFIGURATION);
			generatorConfigurationComp.addElement(generatorConfiguration);
		}
	}

	private void doEditButton() {
		List<IGeneratorConfiguration> selectedElements = generatorConfigurationComp
				.getSelectedElements();
		if (selectedElements == null || selectedElements.size() < 1) {
			return;
		}
		IGeneratorConfiguration selectedGeneratorConfiguration = selectedElements
				.get(0);
		TaskModel taskModel = new TaskModel();
		if (showWizard(taskModel, selectedGeneratorConfiguration) == Window.OK) {
			IGeneratorConfiguration generatorConfiguration = (IGeneratorConfiguration) taskModel
					.getObject(TaskModel.TASK_GENERATOR_CONFIGURATION);
			generatorConfigurationComp.replaceElement(
					selectedGeneratorConfiguration, generatorConfiguration);
		}
	}

	/**
	 * Remove button clicked. Remove the whole selected generator configuration.
	 */
	private void doRemoveButton() {
		List<IGeneratorConfiguration> selectedElements = generatorConfigurationComp
				.getSelectedElements();
		if (selectedElements == null || selectedElements.size() < 1) {
			return;
		}
		generatorConfigurationComp.removeElements(selectedElements);

	}

	/**
	 * Returns the edited {@link IProject}.
	 * 
	 * @return
	 */
	private IProject getProject() {
		IAdaptable adaptable = getElement();
		if (adaptable != null) {
			IProject elem = (IProject) adaptable.getAdapter(IProject.class);
			if (elem != null)
				return elem;
		}
		return null;
	}

	private IJM2TProject getJM2Project() {
		return JM2TCore.create(getProject());
	}

	protected int showWizard(TaskModel taskModel,
			final IGeneratorConfiguration generatorConfiguration) {
		String title = null;
		WizardFragment fragment = null;
		taskModel.putObject(TaskModel.TASK_JM2T_PROJECT,
				JM2TCore.create(getProject()));
		if (generatorConfiguration == null) {
			// Add New generator configuration
			title = Messages.wizNewGeneratorConfigurationWizardTitle;
			fragment = new WizardFragment() {
				protected void createChildFragments(List<WizardFragment> list) {
					list.add(new SelectGeneratorTypeWizardFragment());
					list.add(new SelectModelConverterTypeWizardFragment());
					list.add(WizardTaskUtil.SaveRuntimeFragment);
				}
			};
		} else {
			// Edit selected generator configuration
			title = Messages.wizEditGeneratorConfigurationWizardTitle;
			final WizardFragment fragment2 = getEditGeneratorConfigurationWizardFragment(generatorConfiguration);
			taskModel.putObject(TaskModel.TASK_GENERATOR_CONFIGURATION,
					generatorConfiguration);
			fragment = new WizardFragment() {
				protected void createChildFragments(List<WizardFragment> list) {
					list.add(fragment2);
					list.add(WizardTaskUtil.SaveRuntimeFragment);
				}
			};
		}
		TaskWizard wizard = new TaskWizard(title, fragment, taskModel);
		wizard.setForcePreviousAndNextButtons(true);
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		return dialog.open();
	}

	private WizardFragment getEditGeneratorConfigurationWizardFragment(
			IGeneratorConfiguration generatorConfiguration) {
		WizardFragment fragment = JM2TUI
				.getWizardFragment(generatorConfiguration.getGeneratorType()
						.getId());
		if (fragment != null) {
			return fragment;
		}
		return new NewGeneratorConfigurationWizardFragment();
	}

	@Override
	public boolean performOk() {
		// Save JM2T settings.
		IJM2TProject project = JM2TCore.create(getProject());
		try {
			project.setRawGeneratorConfiguration(
					generatorConfigurationComp.getElements(), null);
		} catch (CoreException e) {
			EclipseUtil.openError(super.getShell(),
					Messages.savingSettingsError, JM2TUI.createStatus(e));
		}
		return true;
	}
}
