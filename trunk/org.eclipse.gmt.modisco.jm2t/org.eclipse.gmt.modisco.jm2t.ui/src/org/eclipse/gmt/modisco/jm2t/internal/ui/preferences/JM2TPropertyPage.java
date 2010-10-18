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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.JM2TUI;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.SWTUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorConfigurationsComposite;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorTypeContentProvider;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorTypeLabelProvider;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.TaskWizard;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * JM2T Property page used to add/edit/remove Generator configurations.
 * 
 */
public class JM2TPropertyPage extends PropertyPage {

	private Button addButton;
	private Button editButton;
	private Button removeButton;

	private IGeneratorType selectedGeneratorType = null;
	
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

		// TODO : manage help
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

		// Global description label
		Label label = new Label(composite, SWT.WRAP);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		label.setLayoutData(data);
		label.setText(Messages.preferenceGeneratorConfigurationsDescription);

		Composite generatorTypesComposite = new Composite(composite, SWT.FILL);
		layout = new GridLayout();
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(4);
		layout.verticalSpacing = convertVerticalDLUsToPixels(3);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.numColumns = 2;
		generatorTypesComposite.setLayout(layout);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		generatorTypesComposite.setLayoutData(data);
		label = new Label(generatorTypesComposite, SWT.WRAP);
		// data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		// data.horizontalSpan = 2;
		// label.setLayoutData(data);
		label.setText(Messages.preferenceGeneratorTypes);

		// Combo with list of generator type.
		IGeneratorType[] generatorTypes = JM2TCore.getGeneratorManager()
				.getGeneratorTypes();
		
		Combo combo = new Combo(generatorTypesComposite, SWT.READ_ONLY | SWT.DROP_DOWN);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		// data.horizontalSpan = 2;
		combo.setLayoutData(data);		
		
		final ComboViewer comboViewer = new ComboViewer(combo);
		comboViewer.setLabelProvider(new GeneratorTypeLabelProvider());
		comboViewer.setContentProvider(new GeneratorTypeContentProvider(
				generatorTypes));
		comboViewer.setInput(generatorTypes);

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection)comboViewer.getSelection();
				selectedGeneratorType = (IGeneratorType)selection.getFirstElement();
			}
		});
		
		// Description label for list of generator configuration
		label = new Label(composite, SWT.WRAP);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.verticalIndent = 5;
		label.setLayoutData(data);
		label.setText(Messages.preferenceGeneratorConfigurationsTable);

		IJM2TProject project = JM2TCore.create(getProject());
		final GeneratorConfigurationsComposite runtimeComp = new GeneratorConfigurationsComposite(
				project,
				composite,
				SWT.NONE,
				new GeneratorConfigurationsComposite.GeneratorLaunchConfigurationSelectionListener() {

					public void generatorConfigurationSelected(
							IGeneratorConfiguration runtime) {
						// if (runtime == null) {
						// edit.setEnabled(false);
						// remove.setEnabled(false);
						// pathLabel.setText("");
						// } else {
						// IStatus status = runtime
						// .validate(new NullProgressMonitor());
						// if (status != null
						// && status.getSeverity() == IStatus.ERROR) {
						// Color c = pathLabel.getDisplay()
						// .getSystemColor(SWT.COLOR_RED);
						// pathLabel.setForeground(c);
						// pathLabel.setText(status.getMessage());
						// } else if (runtime.getLocation() != null) {
						// pathLabel.setForeground(edit.getForeground());
						// pathLabel.setText(runtime.getLocation() + "");
						// } else
						// pathLabel.setText("");
						//
						// if (runtime.isReadOnly()) {
						// edit.setEnabled(false);
						// remove.setEnabled(false);
						// } else {
						// if (runtime.getRuntimeType() != null)
						// edit.setEnabled(ServerUIPlugin
						// .hasWizardFragment(runtime
						// .getRuntimeType().getId()));
						// else
						// edit.setEnabled(false);
						// remove.setEnabled(true);
						// }
						// }
					}
				});
		runtimeComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL));

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

		addButton = SWTUtil.createButton(buttonComp, Messages.addButton);
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (showWizard(null) == Window.CANCEL)
					return;
				runtimeComp.refresh();
			}
		});

		editButton = SWTUtil.createButton(buttonComp, Messages.editButton);
		editButton.setEnabled(false);
		editButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// IRuntime runtime = runtimeComp.getSelectedRuntime();
				// if (runtime != null) {
				// IRuntimeWorkingCopy runtimeWorkingCopy =
				// runtime.createWorkingCopy();
				// if (showWizard(runtimeWorkingCopy) != Window.CANCEL) {
				// try {
				// runtimeComp.refresh(runtime);
				// } catch (Exception ex) {
				// // ignore
				// }
				// }
				// }
			}
		});

		removeButton = SWTUtil.createButton(buttonComp, Messages.removeButton);
		removeButton.setEnabled(false);
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// IRuntime runtime = runtimeComp.getSelectedRuntime();
				// if (removeRuntime(runtime))
				// runtimeComp.remove(runtime);
			}
		});
		Dialog.applyDialogFont(composite);

		return composite;
	}

	private int showWizard(Object object) {
		if (selectedGeneratorType == null) {
			editButton.setEnabled(false);
			return Window.CANCEL;
		}
		String title = "AA";
		WizardFragment fragment = null;
		TaskModel taskModel = new TaskModel();
		
		final WizardFragment fragment2 = JM2TUI
				.getWizardFragment(selectedGeneratorType.getId());
		if (fragment2 == null) {
			editButton.setEnabled(false);
			return Window.CANCEL;
		}

		// Object a = null;
		// taskModel.putObject(TaskModel.TASK_RUNTIME, a);
		fragment = new WizardFragment() {
			protected void createChildFragments(List<WizardFragment> list) {
				list.add(fragment2);
				// list.add(WizardTaskUtil.SaveRuntimeFragment);
			}
		};

		TaskWizard wizard = new TaskWizard(title, fragment, taskModel);
		wizard.setForcePreviousAndNextButtons(true);
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		return dialog.open();

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
}
