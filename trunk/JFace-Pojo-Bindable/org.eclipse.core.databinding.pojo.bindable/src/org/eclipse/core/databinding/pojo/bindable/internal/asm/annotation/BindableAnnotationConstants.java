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
package org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;

/**
 * Properties name of {@link Bindable} annotation.
 * 
 */
public interface BindableAnnotationConstants {

	String BINDABLE_VALUE_ANNOTATION = "value";
	String BINDABLE_DEPENDS_ON_ANNOTATION = "dependsOn";
	String BINDABLE_FIREEVENTS_ON_ANNOTATION = "fireEvents";
}
