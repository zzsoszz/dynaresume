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
package org.eclipse.gmt.modisco.jm2t.ui.wizard.fragment;

import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.page.NewGeneratorConfigurationComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * Wizard fragment used to create a new generator configuration. It's the last
 * page to create a generator configuration.
 * 
 */
public class NewGeneratorConfigurationWizardFragment extends WizardFragment {

	private NewGeneratorConfigurationComposite comp;

	@Override
	public Composite createComposite(Composite parent, IWizardHandle handle) {
		comp = new NewGeneratorConfigurationComposite(parent, handle,
				getTaskModel());
		return comp;
	}

	public boolean hasComposite() {
		return true;
	}

	@Override
	public void enter() {
		if (comp != null) {
			IGeneratorConfiguration generatorConfiguration = (IGeneratorConfiguration) getTaskModel()
					.getObject(TaskModel.TASK_GENERATOR_CONFIGURATION);
			if (generatorConfiguration != null) {
				comp.init(generatorConfiguration);
			} else {
				IGeneratorType generatorType = (IGeneratorType) getTaskModel()
						.getObject(TaskModel.TASK_GENERATOR_TYPE);
				IModelConverterType modelConverterType = (IModelConverterType) getTaskModel()
						.getObject(TaskModel.TASK_MODEL_CONVERTER_TYPE);
				comp.init(generatorType, modelConverterType);
			}
		}
	}
}
