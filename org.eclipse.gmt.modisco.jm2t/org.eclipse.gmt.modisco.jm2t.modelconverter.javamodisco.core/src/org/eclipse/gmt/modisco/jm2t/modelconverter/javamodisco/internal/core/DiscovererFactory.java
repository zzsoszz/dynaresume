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
package org.eclipse.gmt.modisco.jm2t.modelconverter.javamodisco.internal.core;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;

public class DiscovererFactory {

	private static DiscovererFactory INSTANCE = new DiscovererFactory();

	public static DiscovererFactory getFactory() {
		return INSTANCE;
	}

	public IExtendedDiscover createDiscoverer(IJavaElement element) {
		int type = element.getElementType();
		switch (type) {
		case IJavaElement.JAVA_PROJECT:
			return new ExtendedDiscoverJavaModelFromJavaProject(
					(IJavaProject) element);
		case IJavaElement.COMPILATION_UNIT:
			return new ExtendedDiscoverJavaModelFromCompilationUnit(
					(ICompilationUnit) element);
		case IJavaElement.CLASS_FILE:
			return new ExtendedDiscoverJavaModelFromClassFile(
					(IClassFile) element);
		case IJavaElement.TYPE:
			return createDiscoverer(element.getParent());
		case IJavaElement.PACKAGE_FRAGMENT:
			IPackageFragment packageFragment = (IPackageFragment) element;
			break;
		case IJavaElement.PACKAGE_FRAGMENT_ROOT:
			// src folder is selected
			IPackageFragmentRoot packageFragmentRoot = (IPackageFragmentRoot) element;
			return createDiscoverer(packageFragmentRoot.getParent());
		}		 		
		return null;
	}
}
