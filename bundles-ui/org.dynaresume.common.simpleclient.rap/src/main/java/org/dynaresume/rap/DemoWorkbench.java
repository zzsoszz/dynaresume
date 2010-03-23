/*******************************************************************************
 * Copyright (c) 2002, 2008 Innoopract Informationssysteme GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Innoopract Informationssysteme GmbH - initial API and implementation
 ******************************************************************************/

package org.dynaresume.rap;

import org.dynaresume.ApplicationWorkbenchAdvisor;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

public class DemoWorkbench implements IEntryPoint {

	public int createUI() {
		WorkbenchAdvisor worbenchAdvisor = new ApplicationWorkbenchAdvisor();

		worbenchAdvisor = new ApplicationWorkbenchAdvisor();
		Display display = PlatformUI.createDisplay();
		int result = PlatformUI.createAndRunWorkbench(display, worbenchAdvisor);
		display.dispose();
		return result;
	}
}
