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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.swt.widgets.Composite;

/**
 * Abstract class generator configuration wizard fragment.
 * 
 */
public abstract class GeneratorConfigurationWizardFragment extends
		WizardFragment {

	protected GeneratorConfigurationComposite comp;

	public GeneratorConfigurationWizardFragment() {
		// do nothing
	}

	public boolean hasComposite() {
		return true;
	}

	public boolean isComplete() {
		IGeneratorConfiguration configuration = (IGeneratorConfiguration) getTaskModel()
				.getObject(TaskModel.TASK_GENERATOR_CONFIGURATION);
		if (configuration == null)
			return false;
		IStatus status = configuration.validate(null);
		return (status == null || status.getSeverity() != IStatus.ERROR);
	}

	public Composite createComposite(Composite parent, IWizardHandle wizard) {
		comp = createGeneratorConfigurationComposite(parent, wizard);
		return comp;
	}

	protected GeneratorConfigurationComposite createGeneratorConfigurationComposite(
			Composite parent, IWizardHandle wizard) {
		return new GeneratorConfigurationComposite(parent, wizard);
	}

	public void enter() {
		if (comp != null) {
			IGeneratorConfiguration configuration = (IGeneratorConfiguration) getTaskModel()
					.getObject(TaskModel.TASK_GENERATOR_CONFIGURATION);
			comp.setGeneratorConfiguration(configuration);
		}
	}

	public void exit() {
		// IRuntimeWorkingCopy runtime = (IRuntimeWorkingCopy)
		// getTaskModel().getObject(TaskModel.TASK_RUNTIME);
		// IPath path = runtime.getLocation();
		// if (runtime.validate(null).getSeverity() != IStatus.ERROR)
		// TomcatPlugin.setPreference("location" +
		// runtime.getRuntimeType().getId(), path.toString());
	}
}
