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
package org.eclipse.gmt.modisco.jm2t.modelconverter.internal.javamodisco;

import org.eclipse.osgi.util.NLS;

/**
 * Messages used in the Plug-In JM2TCore.
 * 
 */
public class Messages extends NLS {

	public static String CannotConvertToJavaELement;

	private static final String BUNDLE_NAME = "org.eclipse.gmt.modisco.jm2t.modelconverter.internal.javamodisco.messages"; //$NON-NLS-1$

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
