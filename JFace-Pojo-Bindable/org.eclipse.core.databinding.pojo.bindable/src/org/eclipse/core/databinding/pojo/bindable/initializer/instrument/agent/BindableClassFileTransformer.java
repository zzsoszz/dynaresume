/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *     Joga Singh <joga.singh@gmail.com> Added exception handling and optional debugging help
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.initializer.instrument.agent;

import java.beans.PropertyChangeSupport;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.initializer.AbstractBindableClassTransformer;

/**
 * 
 * {@link ClassFileTransformer} implementation which transform bytecode (by
 * using ASM) of simple POJO to implements {@link BindableAware} interface and
 * add {@link PropertyChangeSupport}.
 * 
 */
public class BindableClassFileTransformer extends AbstractBindableClassTransformer
		implements ClassFileTransformer {

	private BindableStrategy bindableStrategy;

	public BindableClassFileTransformer(BindableStrategy bindableStrategy) {
		this.bindableStrategy = bindableStrategy;
	}

	public BindableStrategy getBindableStrategy() {
		return bindableStrategy;
	}

	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		return super.transform(className, classfileBuffer);
	}

}
