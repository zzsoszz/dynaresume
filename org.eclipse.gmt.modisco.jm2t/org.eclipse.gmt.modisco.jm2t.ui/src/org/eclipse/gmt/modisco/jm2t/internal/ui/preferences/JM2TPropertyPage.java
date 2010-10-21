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

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.JM2TUI;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.DialogField;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.IDialogFieldListener;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.IListAdapter;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogfields.ListDialogField;
import org.eclipse.gmt.modisco.jm2t.internal.ui.dialogs.GeneratorConfigurationDialog;
import org.eclipse.gmt.modisco.jm2t.internal.ui.util.EclipseUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorConfigurationTableLabelProvider;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorTypeContentProvider;
import org.eclipse.gmt.modisco.jm2t.internal.ui.viewers.GeneratorTypeLabelProvider;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.TaskWizard;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.WizardTaskUtil;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.fragment.NewGeneratorConfigurationWizardFragment;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * JM2T Property page used to add/edit/remove Generator configurations.
 * 
 */
public class JM2TPropertyPage extends PropertyPage implements
		IListAdapter<IGeneratorConfiguration>, IDialogFieldListener {

	private static final int IDX_ADD = 0;
	private static final int IDX_EDIT = 1;
	private static final int IDX_REMOVE = 2;

	private IGeneratorType selectedGeneratorType = null;
	private ListDialogField<IGeneratorConfiguration> generatorConfigurationList;

	/**
	 * GeneratorConfigurationPreferencesPage constructor comment.
	 */
	public JM2TPropertyPage() {
		super();
		noDefaultAndApplyButton();

		// Instantiate buttons with list of generator configuration
		String[] buttons = new String[] { Messages.addButton,
				Messages.editButton, Messages.removeButton };
		generatorConfigurationList = new ListDialogField<IGeneratorConfiguration>(
				this, buttons, new GeneratorConfigurationTableLabelProvider());
		generatorConfigurationList.setDialogFieldListener(this);
		generatorConfigurationList.setRemoveButtonIndex(IDX_REMOVE);

		String[] columnsHeaders = new String[] { Messages.columnName,
				Messages.columnType, };

		generatorConfigurationList
				.setTableColumns(new ListDialogField.ColumnsDescription(
						columnsHeaders, true));
		// generatorConfigurationList.setViewerComparator(new
		// GeneratorLaunchConfigurationViewerSorter());

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

		// Create Generator type panel
		createGeneratorTypePanel(composite);

		// Create Generator configuration list panel
		createGeneratorConfigurationListPanel(composite);

		Dialog.applyDialogFont(composite);

		return composite;
	}

	/**
	 * Create combo generator type.
	 * 
	 * @param composite
	 */
	private void createGeneratorTypePanel(Composite composite) {
		GridLayout layout;
		GridData data;
		Label label;
		// Generator type combo
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

		Combo combo = new Combo(generatorTypesComposite, SWT.READ_ONLY
				| SWT.DROP_DOWN);
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
				IStructuredSelection selection = (IStructuredSelection) comboViewer
						.getSelection();
				selectedGeneratorType = (IGeneratorType) selection
						.getFirstElement();
			}
		});

		if (generatorTypes.length > 0) {
			// Select the first generator type
			selectedGeneratorType = generatorTypes[0];
			comboViewer.setSelection(new StructuredSelection(
					selectedGeneratorType));
		}
		if (comboViewer.getSelection().isEmpty()) {
			// Disable the buttons add/edit/remove
			generatorConfigurationList.enableButton(IDX_ADD, false);
			generatorConfigurationList.enableButton(IDX_EDIT, false);
			generatorConfigurationList.enableButton(IDX_REMOVE, false);
		} else {
			// Disable the buttons edit/remove
			generatorConfigurationList.enableButton(IDX_ADD, true);
			generatorConfigurationList.enableButton(IDX_EDIT, false);
			generatorConfigurationList.enableButton(IDX_REMOVE, false);
		}
	}

	/**
	 * Create Generator configuration list panel
	 * 
	 * @param composite
	 */
	private void createGeneratorConfigurationListPanel(Composite composite) {
		GridData data;
		Label label;
		// Description label for list of generator configuration
		label = new Label(composite, SWT.WRAP);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.verticalIndent = 5;
		label.setLayoutData(data);
		label.setText(Messages.preferenceGeneratorConfigurationsTable);

		// PixelConverter conv= new PixelConverter(composite);
		data = new GridData(GridData.FILL_BOTH);
		// data.widthHint= conv.convertWidthInCharsToPixels(50);
		Control listControl = generatorConfigurationList
				.getListControl(composite);
		listControl.setLayoutData(data);

		Control buttonsControl = generatorConfigurationList
				.getButtonBox(composite);
		buttonsControl.setLayoutData(new GridData(
				GridData.HORIZONTAL_ALIGN_FILL
						| GridData.VERTICAL_ALIGN_BEGINNING));

		// Load generator configuration from .jm2tsettings file to populate
		// generator configuration list.
		IJM2TProject project = JM2TCore.create(getProject());
		generatorConfigurationList.setElements(project
				.readGeneratorConfigurations());

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

	// ----------------- Implements IListAdapter + IDialogFieldListener

	private boolean canEdit(List<IGeneratorConfiguration> selectedElements) {
		return selectedElements.size() == 1;
	}

	public void customButtonPressed(
			ListDialogField<IGeneratorConfiguration> field, int index) {
		handleButtonPressed(index);
	}

	public void selectionChanged(ListDialogField<IGeneratorConfiguration> field) {
		List<IGeneratorConfiguration> selectedElements = field
				.getSelectedElements();
		field.enableButton(IDX_EDIT, canEdit(selectedElements));
	}

	public void doubleClicked(ListDialogField<IGeneratorConfiguration> field) {
		if (canEdit(field.getSelectedElements())) {
			handleButtonPressed(IDX_EDIT);
		}
	}

	public void dialogFieldChanged(DialogField field) {
		// updateModel(field);
	}

	// ----------------- Handle button clicked.

	/**
	 * 
	 * @param index
	 */
	private void handleButtonPressed(int index) {
		switch (index) {
		case IDX_ADD:
			doAddButton();
			break;
		case IDX_EDIT:
			doEditButton();
			break;
		case IDX_REMOVE:
			doRemoveButton();
			break;
		}
	}

	private void doAddButton() {
		if (showWizard(null) == Window.OK) {

		}

		// GeneratorConfigurationDialog dialog = new
		// GeneratorConfigurationDialog(
		// getShell(), JM2TCore.create(getProject()),
		// selectedGeneratorType, generatorConfigurationList.getElements());
		// if (dialog.open() == Window.OK) {
		// generatorConfigurationList.addElement(dialog.getResult());
		// }
	}

	private void doEditButton() {
		IGeneratorConfiguration selectedGeneratorConfiguration = generatorConfigurationList
				.getSelectedElements().get(0);

		GeneratorConfigurationDialog dialog = new GeneratorConfigurationDialog(
				getShell(), JM2TCore.create(getProject()),
				selectedGeneratorConfiguration,
				generatorConfigurationList.getElements());
		if (dialog.open() == Window.OK) {
			generatorConfigurationList.replaceElement(
					selectedGeneratorConfiguration, dialog.getResult());
		}
	}

	/**
	 * Remove button clicked. Remove the whole selected generator configuration.
	 */
	private void doRemoveButton() {
		Collection<IGeneratorConfiguration> selectedConfigurations = generatorConfigurationList
				.getSelectedElements();
		if (selectedConfigurations.isEmpty()) {
			return;
		}
		for (IGeneratorConfiguration selectedGeneratorConfiguration : selectedConfigurations) {
			generatorConfigurationList
					.removeElement(selectedGeneratorConfiguration);
		}
	}

	@Override
	public boolean performOk() {
		// Save JM2T settings.
		IJM2TProject project = JM2TCore.create(getProject());
		try {
			project.setRawGeneratorConfiguration(
					generatorConfigurationList.getElements(), null);
		} catch (CoreException e) {
			EclipseUtil.openError(super.getShell(),
					Messages.savingSettingsError, JM2TUI.createStatus(e));
		}
		return true;
	}

	protected int showWizard(
			final IGeneratorConfiguration generatorConfiguration) {
		String title = null;
		WizardFragment fragment = null;
		TaskModel taskModel = new TaskModel();
		if (generatorConfiguration == null) {
			// Add New generator configuration
			title = Messages.wizNewGeneratorConfigurationWizardTitle;
			fragment = new WizardFragment() {
				protected void createChildFragments(List<WizardFragment> list) {
					list.add(new NewGeneratorConfigurationWizardFragment());
					list.add(WizardTaskUtil.SaveRuntimeFragment);
				}
			};
		} else {
			// Edit selected generator configuration
			title = Messages.wizEditGeneratorConfigurationWizardTitle;
			final WizardFragment fragment2 = JM2TUI
					.getWizardFragment(generatorConfiguration
							.getGeneratorType().getId());
			if (fragment2 == null) {
				generatorConfigurationList.enableButton(IDX_EDIT, false);
				return Window.CANCEL;
			}
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
}
