/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.internal.ui.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;

/**
 * 
 */
public class WizardTaskUtil {

	public static final WizardFragment SaveRuntimeFragment = new WizardFragment() {
		public void performFinish(IProgressMonitor monitor)
				throws CoreException {
			// WizardTaskUtil.saveRuntime(getTaskModel(), monitor);
		}
	};

}