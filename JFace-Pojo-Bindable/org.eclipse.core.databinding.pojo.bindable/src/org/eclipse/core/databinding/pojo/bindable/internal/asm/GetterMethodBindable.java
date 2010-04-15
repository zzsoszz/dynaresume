/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.internal.asm;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.objectweb.asm.MethodVisitor;

/**
 * Getter method wich defined {@link Bindable} annotation to manage dependsOn.
 * 
 */
public class GetterMethodBindable extends AbstractMethodBindable {

	protected GetterMethodBindable(ClassBindable classBindable, int access,
			String methodName, String desc, MethodVisitor mv) {
		super(classBindable, access, methodName, desc, mv);
	}

	@Override
	public void visitEnd() {
		super.visitEnd();
		String[] dependsOn = getBindableAnnotationDependsOn();
		if (dependsOn != null && dependsOn.length > 0) {
			// dependsOn are declared with Bindable annotation, add this
			// GetterMethodBindable to ClassBindable (to generate dependsOn
			// fields)
			getClassBindable().addGetterMethodBindable(this);
		}
	}

}
