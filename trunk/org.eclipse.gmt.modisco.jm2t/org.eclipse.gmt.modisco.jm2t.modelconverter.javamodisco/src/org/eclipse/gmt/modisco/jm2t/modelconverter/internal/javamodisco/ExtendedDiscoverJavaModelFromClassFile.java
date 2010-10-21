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

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.actions.DiscoverJavaModelFromClassFile;
import org.eclipse.jdt.core.IClassFile;

public class ExtendedDiscoverJavaModelFromClassFile extends
		DiscoverJavaModelFromClassFile implements IExtendedDiscover {

	private IClassFile classFile;

	public ExtendedDiscoverJavaModelFromClassFile(IClassFile classFile) {
		this.classFile = classFile;
	}

	@Override
	public void saveResource(URI target, IPath path, Resource resource,
			IProgressMonitor monitor) throws IOException {
		// Don't save EMF JavaModel as XMI file.
	}

	public Object getSource() {
		return classFile;
	}

}
