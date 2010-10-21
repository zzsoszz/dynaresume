package org.eclipse.gmt.modisco.jm2t.internal.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverter;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.core.util.ResourcesUtils;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Trace;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;

public class ContributionItemForJM2TMenu extends ContributionItem {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets
	 * .Menu, int)
	 */
	@Override
	public void fill(final Menu menu, final int index) {
		super.fill(menu, index);

		try {
			IWorkbenchWindow workbenchWindow = (IWorkbenchWindow) menu
					.getShell().getData();
			ISelectionService selectionService = workbenchWindow
					.getSelectionService();
			// ClassCastException in Eclipse 3.5 : TextSelection /
			// IStructuredSelection ...
			if ((selectionService.getSelection() != null)
					&& (selectionService.getSelection() instanceof IStructuredSelection)
					&& (!selectionService.getSelection().isEmpty())) {
				IStructuredSelection selection = (IStructuredSelection) selectionService
						.getSelection();

				Object[] selectedObjects = selection.toArray();

				IProject project = ResourcesUtils.getActiveProject(selection);
				IJM2TProject jm2tProject = JM2TCore.create(project);
				if (jm2tProject == null) {
					return;
				}

				Collection<IGeneratorConfiguration> configurations = jm2tProject
						.getGeneratorConfigurations();
				if (configurations.isEmpty()) {
					return;
				}

				// Create "Java M2T Generator" root menu.
				MenuItem jm2tMenuItem = new MenuItem(menu, SWT.CASCADE, index);
				jm2tMenuItem.setText(Messages.ContributionItemForJM2TMenu_0);

				// modiscoItem.setImage(this.getMoDiscoImage());

				// Add "Java M2T Generator" menu
				Menu jm2tmenu = new Menu(jm2tMenuItem);
				jm2tMenuItem.setMenu(jm2tmenu);

				// Create a sub-menu for each generator configuration.
				for (IGeneratorConfiguration configuration : configurations) {
					createMenuForGeneratorConfiguration(configuration,
							jm2tmenu, selectedObjects);
				}
			}

		} catch (Exception e) {
			Trace.trace(Trace.WARNING, e.getMessage(), e);
		}

	}

	/**
	 * Create sub menu for the generator configiguration.
	 * 
	 * @param configuration
	 * @param jm2tmenu
	 * @param selectedObjects
	 */
	protected void createMenuForGeneratorConfiguration(
			IGeneratorConfiguration configuration, Menu jm2tmenu,
			final Object[] selectedObjects) {

		// Loop for each selected objects to get the real object to convert (ex
		// : IFile is selected and the real object to convert is a
		// IJavaElement).
		IModelConverter modelConverter = null;
		IModelConverterType modelConverterType = configuration
				.getModelConverterType();
		if (modelConverterType != null) {
			modelConverter = modelConverterType.getModelConverter();
		}
		List<Object> selectedObjectToConverts = new ArrayList<Object>();
		Object selectedObjectToConvert = null;
		for (final Object selectedObject : selectedObjects) {
			selectedObjectToConvert = modelConverter
					.getModelToConvert(selectedObject);
			if (selectedObjectToConvert != null) {
				selectedObjectToConverts.add(selectedObjectToConvert);
			}
		}

		if (selectedObjectToConverts.size() < 1) {
			// None selected object can be used as model for this Generator
			// configuration
			// None menu item must be created
			return;
		}

		// There is one or more of selected object which can be used as model
		// for this Generator
		// configuration, create a menu item for this configuration.
		Menu parentMenu = jm2tmenu;

		// final menu
		MenuItem discovererMenu = new MenuItem(parentMenu, SWT.PUSH, 0);
		discovererMenu.setText(configuration.getName());
		// if (discoverer.getImageIcon() != null) {
		// discovererMenu.setImage(discoverer.getImageIcon());
		// }

		MenuListenerHandler handler = new MenuListenerHandler(configuration,
				selectedObjectToConverts.toArray());
		discovererMenu.addSelectionListener(handler);

	}
}
