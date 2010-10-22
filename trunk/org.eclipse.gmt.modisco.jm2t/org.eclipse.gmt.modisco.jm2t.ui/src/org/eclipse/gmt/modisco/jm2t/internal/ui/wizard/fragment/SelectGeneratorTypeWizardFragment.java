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

import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.page.SelectGeneratorTypeComposite;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.swt.widgets.Composite;

/**
 * Wizard fragment used to select a generator type. It's the first page to
 * create a generator configuration.
 * 
 */
public class SelectGeneratorTypeWizardFragment extends WizardFragment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.ui.internal.task.WizardTask#getWizardPage()
	 */
	public Composite createComposite(Composite parent, IWizardHandle wizard) {
		return new SelectGeneratorTypeComposite(parent, wizard,
				getTaskModel());
	}

	public boolean hasComposite() {
		return true;
	}

	@Override
	public boolean isComplete() {
		// Next button is visible when a generator type is selected.
		return (getTaskModel().getObject(TaskModel.TASK_GENERATOR_TYPE) != null);
	}

}
