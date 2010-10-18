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
package org.eclipse.gmt.modisco.jm2t.generator.acceleo2.internal.ui;

import org.eclipse.gmt.modisco.jm2t.ui.wizard.GeneratorConfigurationComposite;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.GeneratorConfigurationWizardFragment;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.swt.widgets.Composite;

/**
 * Extends GeneratorConfigurationWizardFragment to use custom
 * {@link Acceleo2GeneratorConfigurationComposite}.
 * 
 */
public class Acceleo2GeneratorConfigurationWizardFragment extends
		GeneratorConfigurationWizardFragment {

	@Override
	protected GeneratorConfigurationComposite createGeneratorConfigurationComposite(
			Composite parent, IWizardHandle wizard) {
		return new Acceleo2GeneratorConfigurationComposite(parent, wizard);
	}

}
