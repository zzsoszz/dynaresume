/*******************************************************************************
 * Copyright (c) 2009 Martin Lippert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Martin Lippert                   initial implementation      
 *   Angelo ZERR                      manage springweaver into Jpa context
 *******************************************************************************/
package org.eclipse.equinox.weaving.springweaver;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.osgi.framework.Bundle;

/**
 * Wrapper of {@link ClassFileTransformer} wich contains dynamic packages wich
 * must be imported into the {@link Bundle} when transformation is done.
 * 
 */
public class ClassFileTransformerWrapper implements ClassFileTransformer {

	private ClassFileTransformer classFileTransformer;
	private String importDynamicPackages;

	public ClassFileTransformerWrapper(
			ClassFileTransformer classFileTransformer,
			String importDynamicPackages) {
		this.classFileTransformer = classFileTransformer;
		this.importDynamicPackages = importDynamicPackages;
	}

	public String getImportDynamicPackages() {
		return importDynamicPackages;
	}

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		return classFileTransformer.transform(loader, className,
				classBeingRedefined, protectionDomain, classfileBuffer);
	}

}
