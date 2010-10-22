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
package org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.JM2TUI;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.page.SelectModelConverterTypeComposite;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.fragment.NewGeneratorConfigurationWizardFragment;
import org.eclipse.swt.widgets.Composite;

public class SelectModelConverterTypeWizardFragment extends WizardFragment {

	protected Map<String, WizardFragment> fragmentMap = new HashMap<String, WizardFragment>();

	private SelectModelConverterTypeComposite comp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.ui.internal.task.WizardTask#getWizardPage()
	 */
	public Composite createComposite(Composite parent, IWizardHandle wizard) {
		comp = new SelectModelConverterTypeComposite(parent, wizard,
				getTaskModel());
		return comp;
	}

	public boolean hasComposite() {
		return true;
	}

	@Override
	public void enter() {
		if (comp != null) {
			IGeneratorType generatorType = (IGeneratorType) getTaskModel()
					.getObject(TaskModel.TASK_GENERATOR_TYPE);
			comp.setGeneratorType(generatorType);
		}
	}

	public List<WizardFragment> getChildFragments() {
		List<WizardFragment> listImpl = new ArrayList<WizardFragment>();
		createChildFragments(listImpl);
		return listImpl;
	}

	protected void createChildFragments(List<WizardFragment> list) {
		if (getTaskModel() == null)
			return;

		IModelConverterType modelConverterType = (IModelConverterType) getTaskModel()
				.getObject(TaskModel.TASK_MODEL_CONVERTER_TYPE);
		if (modelConverterType == null)
			return;

		IGeneratorType generatorType = (IGeneratorType) getTaskModel()
				.getObject(TaskModel.TASK_GENERATOR_TYPE);
		if (generatorType == null)
			return;

		WizardFragment sub = getWizardFragment(generatorType.getId());
		if (sub != null) {
			list.add(sub);
		} else {
			list.add(new NewGeneratorConfigurationWizardFragment());
		}

		// IServerWorkingCopy server = (IServerWorkingCopy)
		// getTaskModel().getObject(TaskModel.TASK_SERVER);
		// if (server != null) {
		// if (server.getServerType().hasServerConfiguration() && server
		// instanceof ServerWorkingCopy) {
		// ServerWorkingCopy swc = (ServerWorkingCopy) server;
		// try {
		// if (runtime.getLocation() != null &&
		// !runtime.getLocation().isEmpty())
		// swc.importRuntimeConfiguration(runtime, null);
		// } catch (CoreException ce) {
		// // ignore
		// }
		// }
		//
		// list.add(new WizardFragment() {
		// public void enter() {
		// IRuntimeWorkingCopy runtime2 = (IRuntimeWorkingCopy)
		// getTaskModel().getObject(TaskModel.TASK_RUNTIME);
		// IServerWorkingCopy server2 = (IServerWorkingCopy)
		// getTaskModel().getObject(TaskModel.TASK_SERVER);
		// server2.setRuntime(runtime2);
		// }
		// });
		//
		// sub = getWizardFragment(server.getServerType().getId());
		// if (sub != null)
		// list.add(sub);
		//
		// list.add(WizardTaskUtil.SaveServerFragment);
		// }
	}

	protected WizardFragment getWizardFragment(String typeId) {
		try {
			WizardFragment fragment = fragmentMap.get(typeId);
			if (fragment != null)
				return fragment;
		} catch (Exception e) {
			// ignore
		}

		WizardFragment fragment = JM2TUI.getWizardFragment(typeId);
		if (fragment != null)
			fragmentMap.put(typeId, fragment);
		return fragment;
	}

}
